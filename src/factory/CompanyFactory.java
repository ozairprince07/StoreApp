package factory;

import customWidgets.CompanyListCell;
import database.Const;
import database.DBConnection;
import helper.PathHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import model.Company;
import query.CompanyQuery;
import query.CustomerQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyFactory {

    ObservableList<Company> companyList = FXCollections.observableArrayList();
    private int companyId;
    private ResultSet resultSet;

    public CompanyFactory() {
    }

    public void addCompany(TextField nameField, TextField contactField, TextField addressField) throws SQLException {
        DBConnection.dbExecute(
                CompanyQuery.insertCompany(
                        nameField.getText().trim(),
                        contactField.getText().trim(),
                        addressField.getText().trim()
                )
        );
    }

    public void listViewCompany(ListView<Company> listView) throws SQLException {
        refreshListView(listView);
    }

    public void refreshListView(ListView<Company> listView) throws SQLException {
        // * Set Cell Value Factory
        setCellFactory(listView);

        // * Get all the companies from the database
        resultSet = DBConnection.dbExecuteQuery(CompanyQuery.getCompanies());

        while (resultSet.next()) {
            companyList.add(
                    new Company(
                            resultSet.getString(Const.COMPANY_NAME),
                            resultSet.getString(Const.COMPANY_PHONE),
                            resultSet.getString(Const.COMPANY_ADDRESS)
                    )
            );
            // * Set data to listview
            listView.setItems(companyList);
        }

    }

    public void setCompanyDetails(ListView<Company> listView, ImageView imageView, TextField nameField, TextField contactField, TextField addressFiled, Company selectedCompany) {
        selectedCompany = listView.getSelectionModel().getSelectedItem();
        Image image = new Image(getClass().getResource(PathHelper.CUSTOMER_IMAGE_FILE).toString());
        imageView.setImage(image);
        nameField.setText(selectedCompany.getCompanyName());
        contactField.setText("Ph: " + selectedCompany.getCompanyContact());
        addressFiled.setText(selectedCompany.getCompanyAddress());
    }

    public void searchCompany(ListView<Company> listView, TextField searchField) {

        FilteredList<Company> filteredList = new FilteredList<>(companyList);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(comapanyModel -> {
                // * Check if the value of search field is to be null
                if (newValue.isBlank() || newValue.isEmpty() || newValue.equals("null")) {
                    return true;
                }

                String keyword = newValue.toLowerCase();

                if (comapanyModel.getCompanyName().toLowerCase().contains(keyword)) {
                    return true;
                } else if (comapanyModel.getCompanyContact().toLowerCase().contains(keyword)) {
                    return true;
                } else
                    return comapanyModel.getCompanyAddress().toLowerCase().contains(keyword);
            });
        });

        SortedList<Company> sortedList = new SortedList<>(filteredList);
        listView.setItems(sortedList);

    }

    public void deleteCompany(Company selectedCompany) throws SQLException {

        // * Get Company Id from the selected item
        resultSet = DBConnection.dbExecuteQuery(
                CompanyQuery.getCompanyId(selectedCompany.getCompanyContact())
        );

        while (resultSet.next()) {
            companyId = resultSet.getInt(Const.CUSTOMER_ID);
        }

        // * Delete Company with Specific Company ID
        DBConnection.dbExecute(
                CustomerQuery.deleteCustomer(companyId)
        );

    }

    private void setCellFactory(ListView<Company> listView) {
        listView.setCellFactory(new Callback<ListView<Company>, ListCell<Company>>() {
            @Override
            public ListCell<Company> call(ListView<Company> companyListView) {
                return new CompanyListCell();
            }
        });
    }

}
