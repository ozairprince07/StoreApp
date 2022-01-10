package controller;

import database.Const;
import database.DBConnection;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconName;
import factory.InvoiceFactory;
import helper.PathHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import model.Customer;
import model.Invoice;
import model.OrderFactory;
import model.Product;
import query.CustomerQuery;
import query.InvoiceQuery;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class InvoiceController implements Initializable {

    public TextField customerField, netDiscountField;
    public Label totalLabel;
    public Button addItemBtn, printBtn, savePrintBtn, saveClrBtn, prevBtn, executeBtn;
    public TableView<Invoice> invoiceTable;
    public TableColumn<Invoice, String> colCode, colName;
    public TableColumn<Invoice, Integer> colQty;
    public TableColumn<Invoice, Double> colUnit, colDiscount, colTotal;

    ObservableList<Invoice> invoiceList = FXCollections.observableArrayList();

    ResultSet resultSet;
    int customerId;

    InvoiceFactory invoiceFactory = new InvoiceFactory();
    OrderFactory orderFactory = new OrderFactory();
    LocalDateTime localDateTime;
    Product selectedProduct;
    Customer selectCustomer;

    VBox productBox = new VBox();
    TextField productSearchFiled = new TextField();
    TableView<Product> productTable = new TableView<>();
    TableColumn<Product, String>
            codeCol = new TableColumn<>("Code"),
            nameCol = new TableColumn<>("Name");
    TableColumn<Product, Integer>
            qtyCol = new TableColumn<>("Qty");
    TableColumn<Product, Double>
            unitCol = new TableColumn<>("Unit Price"),
            costCol = new TableColumn<>("Cost Price");

    VBox customerBox = new VBox();
    TextField customerSearchField = new TextField();
    TableView<Customer> customerTable = new TableView<>();
    TableColumn<Customer, String>
            c_nameCol = new TableColumn<>("Name"),
            c_addressCol = new TableColumn<>("Address");
    TableColumn<Customer, String>
            c_phone = new TableColumn<>("Phone");
    TableColumn<Customer, Double>
            c_credit = new TableColumn<>("Credit"),
            c_debit = new TableColumn<>("Debit");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        netDiscountField.setText("0.0");

        // * Execute Btn Action
        executeBtn.setOnAction(e -> {
        });

        // * Save Btn Action
        saveClrBtn.setOnAction(e -> {

            try {
                DBConnection.dbExecute(
                        InvoiceQuery.insertInvoice(
                                getCustomerId(),
                                localDateTime = LocalDateTime.now(),
                                Double.parseDouble(netDiscountField.getText()),
                                Double.parseDouble(totalLabel.getText())
                        )
                );
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            invoiceList.clear();
            invoiceTable.getItems().clear();
            customerField.clear();
        });

    }

    // * Product Information

    @FXML
    public void addItemBtnAction(ActionEvent e) throws IOException, SQLException {
        invoiceFactory.showProductPopup(addItemBtn, setProductContent());
        invoiceFactory.setProductTable(productTable, codeCol, nameCol, qtyCol, unitCol, costCol, productSearchFiled);
    }

    public Node setProductContent() {
        productTable.getColumns().clear();
        productTable.getColumns().addAll(codeCol, nameCol, qtyCol, unitCol, costCol);
        setProductContextMenu(productTable);
        productBox.getChildren().clear();
        productBox.getChildren().addAll(productSearchFiled, productTable);
        productBox.setSpacing(10);
        productBox.getStylesheets().add(PathHelper.CSS_MAIN);
        productBox.getStyleClass().add("back");
        productBox.setPadding(new Insets(10));
        return productBox;
    }

    public void setProductContextMenu(TableView<Product> tableView) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem addItem = new MenuItem();
        Label addLabel = new Label("Add Item");
        FontAwesomeIcon addIcon = new FontAwesomeIcon();

        addIcon.setIcon(FontAwesomeIconName.PLUS_SQUARE);
        addIcon.setGlyphStyleClass("glyph-icon");
        addLabel.setGraphic(addIcon);
        addLabel.setGraphicTextGap(20);

        addItem.setGraphic(addLabel);

        invoiceFactory.setProductCellFactory(
                colCode, colName, colUnit, colQty, colDiscount, colTotal
        );

        addItem.setOnAction(e -> {
            selectedProduct = tableView.getSelectionModel().getSelectedItem();
            int qty = 1;
            for (Invoice item : invoiceList) {
                if (item.getCode() == selectedProduct.getCode()) {
                    System.out.println(item.getCode());
                    qty = item.getQty() + 1;
                    item.setQty(qty);
                    item.setTotal(item.getQty() * selectedProduct.getUnitPrice());
                    invoiceTable.getItems().set(invoiceList.indexOf(item), item);
                    return;
                }
            }
            invoiceList.add(
                    new Invoice(
                            selectedProduct.getCode(),
                            selectedProduct.getCode(),
                            qty,
                            selectedProduct.getUnitPrice(),
                            0,
                            selectedProduct.getUnitPrice() * qty)
            );
            invoiceTable.setItems(invoiceList);

        });

        contextMenu.getItems().add(addItem);

        tableView.setOnContextMenuRequested(event -> {
            double x = event.getScreenX();
            double y = event.getScreenY();
            contextMenu.show(tableView.getScene().getWindow(), x, y);
        });
    }

    // * Customer Information

    @FXML
    public void addFieldAction(MouseEvent event) throws SQLException {
        invoiceFactory.showCustomerPopup(customerField, setCustomerContent());
        invoiceFactory.setCustomerTable(customerTable, c_nameCol, c_addressCol, c_phone, c_credit, c_debit, customerSearchField);
    }

    public Node setCustomerContent() {
        customerTable.getColumns().clear();
        customerTable.getColumns().addAll(c_nameCol, c_addressCol, c_phone, c_credit, c_debit);
        setCustomerContextMenu(customerTable);
        customerBox.getChildren().clear();
        customerBox.getChildren().addAll(customerSearchField, customerTable);
        customerBox.setSpacing(10);
        customerBox.getStylesheets().add(PathHelper.CSS_MAIN);
        customerBox.getStyleClass().add("back");
        customerBox.setPadding(new Insets(10));
        return customerBox;
    }

    public void setCustomerContextMenu(TableView<Customer> tableView) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem addItem = new MenuItem();
        Label addLabel = new Label("Add Item");
        FontAwesomeIcon addIcon = new FontAwesomeIcon();

        addIcon.setIcon(FontAwesomeIconName.PLUS_SQUARE);
        addIcon.setGlyphStyleClass("glyph-icon");
        addLabel.setGraphic(addIcon);
        addLabel.setGraphicTextGap(20);

        addItem.setGraphic(addLabel);

        invoiceFactory.setCustomerCellFactory(
                c_nameCol, c_addressCol, c_phone, c_credit, c_debit
        );

        addItem.setOnAction(e -> {
            selectCustomer = customerTable.getSelectionModel().getSelectedItem();
            customerField.setText(selectCustomer.getName());
        });

        contextMenu.getItems().add(addItem);

        tableView.setOnContextMenuRequested(event -> {
            double x = event.getScreenX();
            double y = event.getScreenY();
            contextMenu.show(tableView.getScene().getWindow(), x, y);
        });

    }

    private int getCustomerId() throws SQLException {
        resultSet = DBConnection.dbExecuteQuery(
                CustomerQuery.getCustomerId(
                        selectCustomer.getPhone()
                )
        );

        while (resultSet.next()) {
            customerId = resultSet.getInt(Const.CUSTOMER_PHONE);
        }
        return customerId;
    }

}