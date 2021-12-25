package controller;

import factory.CompanyFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Company;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CompanyController implements Initializable {

    public TextField searchField;
    public ListView<Company> listView;
    public TextField nameField, contactField, addressField;
    public ImageView companyImage;
    public AnchorPane imagePane;
    public Button editBtn, addBtn, saveBtn, resetBtn;

    CompanyFactory companyFactory = new CompanyFactory();
    Company selected;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // * Set Item to Company List View
        try {
            companyFactory.listViewCompany(listView);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // * Set CompanyDetails Details to Details Pane
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Company>() {
            @Override
            public void changed(ObservableValue<? extends Company> observableValue, Company company, Company selectedCompany) {
                companyFactory.setCompanyDetails(
                        listView, companyImage, nameField, contactField, addressField, selectedCompany
                );
                selected = listView.getSelectionModel().getSelectedItem();
                saveBtn.setText("Update");
            }
        });

        listView.getSelectionModel().selectFirst();

        // * Search Company from the listview
        searchField.setOnKeyPressed(e -> {
            companyFactory.searchCompany(listView, searchField);
        });

        imagePane.setOnMouseEntered(e -> {
            editBtn.setVisible(true);
        });

        imagePane.setOnMouseExited(e -> {
            editBtn.setVisible(false);
        });

        addBtn.setOnAction(e -> {
            clearFields();
            listView.getSelectionModel().clearSelection();
            saveBtn.setText("Save");
        });

        saveBtn.setOnAction(e -> {
            if (saveBtn.getText() == "Update") {

            } else if (saveBtn.getText() == "Save") {
                try {
                    companyFactory.addCompany(
                            nameField,
                            contactField,
                            addressField
                    );
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        resetBtn.setOnAction(e -> {
            clearFields();
            listView.getSelectionModel().select(selected);
            companyFactory.setCompanyDetails(
                    listView, companyImage, nameField, contactField, addressField, selected
            );
            saveBtn.setText("Update");
        });

    }

    private void clearFields() {
        nameField.clear();
        contactField.clear();
        addressField.clear();
    }

}
