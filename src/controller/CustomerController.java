package controller;

import factory.CustomerFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Customer;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    public ListView<Customer> listView;
    public TextField searchField;
    public ImageView customerImage;
    public Button editBtn, addBtn, saveBtn, resetBtn;
    public AnchorPane imagePane;

    public TextField nameField, contactField, addressField, emailField, creditLimit, debitLimit;

    CustomerFactory customerFactory = new CustomerFactory();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // * Set Item to Customer List View
        try {
            customerFactory.listViewCustomers(listView);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // * Set Customer Details to Details Pane
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Customer>() {
            @Override
            public void changed(ObservableValue<? extends Customer> observableValue, Customer customer, Customer selectedCustomer) {
                customerFactory.setCustomerDetails(listView, customerImage, nameField, contactField, addressField, emailField, creditLimit, debitLimit, selectedCustomer);
            }
        });

        listView.getSelectionModel().selectFirst();

        // * Search Customers from the listview
        searchField.setOnKeyPressed(e -> {
            customerFactory.searchCustomer(listView, searchField);
        });

        imagePane.setOnMouseEntered(e -> {
            editBtn.setVisible(true);
        });

        imagePane.setOnMouseExited(e -> {
            editBtn.setVisible(false);
        });

        addBtn.setOnAction(e -> {
            nameField.clear();
            contactField.clear();
            addressField.clear();
            emailField.clear();
            creditLimit.clear();
            debitLimit.clear();
            listView.getSelectionModel().clearSelection();
        });

        saveBtn.setOnAction(e -> {
            try {
                customerFactory.addCustomer(
                        nameField, addressField,
                        contactField,
                        creditLimit,
                        debitLimit
                );
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

    }

    public static class ColorRectCell extends ListCell<String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            Rectangle rect = new Rectangle(100, 20);
            if (item != null) {
                rect.setFill(Color.web(item));
                setGraphic(rect);
            }
        }
    }

}
