package factory;

import customWidgets.CustomerListCell;
import database.Const;
import database.DBConnection;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import helper.PathHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import model.Customer;
import query.CustomerQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerFactory {

    ObservableList<Customer> customerList = FXCollections.observableArrayList();
    private int customerId;
    private ResultSet resultSet;

    public CustomerFactory() {

    }

    public void addCustomer(TextField name, TextField address, TextField phone, TextField creditLimit, TextField debitLimit) throws SQLException {
        DBConnection.dbExecute(
                CustomerQuery.insertCustomer(
                        name.getText().trim(),
                        address.getText().trim(),
                        phone.getText().trim(),
                        Double.parseDouble(creditLimit.getText().trim()),
                        Double.parseDouble(debitLimit.getText().trim())
                )
        );
    }

    public void listViewCustomers(ListView<Customer> listView) throws SQLException {
        refreshListView(listView);
    }

    public void refreshListView(ListView<Customer> listView) throws SQLException {
        // * Set Cell Factory to List View
        setCellFactory(listView);

        // * Get all the customer from the database
        resultSet = DBConnection.dbExecuteQuery(
                CustomerQuery.getCustomers()
        );

        while (resultSet.next()) {
            customerList.add(
                    new Customer(
                            resultSet.getString(Const.CUSTOMER_NAME),
                            resultSet.getString(Const.CUSTOMER_ADDRESS),
                            resultSet.getString(Const.CUSTOMER_PHONE),
                            resultSet.getDouble(Const.CUSTOMER_CREDIT_LIMIT),
                            resultSet.getDouble(Const.CUSTOMER_DEBIT_LIMIT)
                    )
            );
            // * Set data to listview
            listView.setItems(customerList);
        }
    }

    public void setCustomerDetails(ListView<Customer> listView, ImageView imageView, TextField nameField, TextField contactField, TextField addressFiled, TextField emailField, TextField creditLimit, TextField debitLimit, Customer selectedCustomer) {
        selectedCustomer = listView.getSelectionModel().getSelectedItem();
        Image image = new Image(getClass().getResource(PathHelper.CUSTOMER_IMAGE_FILE).toString());
        imageView.setImage(image);
        nameField.setText(selectedCustomer.getName());
        contactField.setText("Ph: " + selectedCustomer.getPhone());
        addressFiled.setText(selectedCustomer.getAddress());
        emailField.setText(selectedCustomer.getAddress());
        creditLimit.setText(String.valueOf(selectedCustomer.getCredit()));
        debitLimit.setText(String.valueOf(selectedCustomer.getDebit()));
    }

    public void searchCustomer(ListView<Customer> listView, TextField searchField) {

        FilteredList<Customer> filteredList = new FilteredList<>(customerList);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(customerModel -> {
                // * Check if the value of search field is to be null
                if (newValue.isBlank() || newValue.isEmpty() || newValue.equals("null")) {
                    return true;
                }

                String keyword = newValue.toLowerCase();

                if (customerModel.getName().toLowerCase().contains(keyword)) {
                    return true;
                } else if (customerModel.getPhone().toLowerCase().contains(keyword)) {
                    return true;
                }
//                else if (customerModel.getWhatsapp().toLowerCase().indexOf(keyword) > -1) {
//                    return true;
//                }
                else if (customerModel.getAddress().toLowerCase().contains(keyword)) {
                    return true;
                } else if (String.valueOf(customerModel.getCredit()).toLowerCase().contains(keyword)) {
                    return true;
                } else return String.valueOf(customerModel.getDebit()).toLowerCase().contains(keyword);

            });
        });

        SortedList<Customer> sortedList = new SortedList<>(filteredList);
        listView.setItems(sortedList);

    }

    public void deleteCustomer(Customer selectedCustomer) throws SQLException {

        // * Get Customer Id from the selected item
        resultSet = DBConnection.dbExecuteQuery(
                CustomerQuery.getCustomerId(selectedCustomer.getPhone())
        );

        while (resultSet.next()) {
            customerId = resultSet.getInt(Const.CUSTOMER_ID);
        }

        // * Delete Customer with Specific Customer ID
        DBConnection.dbExecute(
                CustomerQuery.deleteCustomer(customerId)
        );

    }

    private void setCellFactory(ListView<Customer> listView) {
        listView.setCellFactory(new Callback<ListView<Customer>, ListCell<Customer>>() {
            @Override
            public ListCell<Customer> call(ListView<Customer> customerListView) {
                return new CustomerListCell();
            }
        });
    }

    public void tableViewCustomers
            (
                    TableView<Customer> tableView,
                    TableColumn<Customer, String> colName,
                    TableColumn<Customer, String> colAddress,
                    TableColumn<Customer, String> colPhone,
                    TableColumn<Customer, Double> colCredit,
                    TableColumn<Customer, Double> colDebit
            ) throws SQLException {

        refreshCustomerTable(tableView, colName, colAddress, colPhone, colCredit, colDebit);

    }

    // * Tables Data of he Customer
    public void refreshCustomerTable
    (
            TableView<Customer> tableView,
            TableColumn<Customer, String> colName,
            TableColumn<Customer, String> colAddress,
            TableColumn<Customer, String> colPhone,
            TableColumn<Customer, Double> colCredit,
            TableColumn<Customer, Double> colDebit
    ) throws SQLException {

        // * CLear the Customer Observable List
        customerList.clear();

        // * Get the data from database
        resultSet = DBConnection.dbExecuteQuery(
                CustomerQuery.getCustomers()
        );

        // * Set data to Customer Observable List Model
        while (resultSet.next()) {
            customerList.add(
                    new Customer(
                            resultSet.getString(Const.CUSTOMER_NAME),
                            resultSet.getString(Const.CUSTOMER_ADDRESS),
                            resultSet.getString(Const.CUSTOMER_PHONE),
                            resultSet.getDouble(Const.CUSTOMER_CREDIT_LIMIT),
                            resultSet.getInt(Const.CUSTOMER_DEBIT_LIMIT)
                    )
            );
            // * Set data to Tableview
            tableView.setItems(customerList);
        }

        cellFactory(colName, colAddress, colPhone, colCredit, colDebit);

    }

    private void cellFactory
            (
                    TableColumn<Customer, String> colName,
                    TableColumn<Customer, String> colAddress,
                    TableColumn<Customer, String> colPhone,
                    TableColumn<Customer, Double> colCredit,
                    TableColumn<Customer, Double> colDebit
            ) {

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colCredit.setCellValueFactory(new PropertyValueFactory<>("credit"));
        colDebit.setCellValueFactory(new PropertyValueFactory<>("debit"));

    }

    public void searchCustomerTable(TableView<Customer> tableView, TextField searchField, FontAwesomeIcon icon) {
        FilteredList<Customer> filteredList = new FilteredList<>(customerList);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(customerModel -> {
                // * Check if the value of search field is to be null
                if (newValue.isBlank() || newValue.isEmpty() || newValue.equals("null")) {
                    icon.setVisible(false);
                    return true;
                }

                String keyword = newValue.toLowerCase();

                if (customerModel.getName().toLowerCase().contains(keyword)) {
                    return true;
                } else if (customerModel.getPhone().toLowerCase().contains(keyword)) {
                    return true;
                }
//                else if (customerModel.getWhatsapp().toLowerCase().indexOf(keyword) > -1) {
//                    return true;
//                }
                else if (customerModel.getAddress().toLowerCase().contains(keyword)) {
                    return true;
                } else if (String.valueOf(customerModel.getCredit()).toLowerCase().contains(keyword)) {
                    return true;
                } else return String.valueOf(customerModel.getDebit()).toLowerCase().contains(keyword);

            });
        });

        SortedList<Customer> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);

    }

}
