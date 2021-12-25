package controller;

import factory.InvoiceFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InvoiceController implements Initializable {

    public ComboBox<String> customerField;
    public Button addItemBtn;

    InvoiceFactory invoiceFactory = new InvoiceFactory();
    AddItemController addItemController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        invoiceFactory.setCustomerBox(customerField);

        addItemController = new AddItemController();

    }

    @FXML
    public void addItemBtnAction(ActionEvent e) throws IOException, SQLException {
        invoiceFactory.showPopover(addItemBtn);

    }

}
