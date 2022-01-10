package factory;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconName;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import model.Customer;
import model.Invoice;
import model.Product;
import org.controlsfx.control.PopOver;

import java.io.IOException;
import java.sql.SQLException;

public class InvoiceFactory {

    private final ObservableList<Customer> customerList = FXCollections.observableArrayList();
    private final ProductFactory productFactory = new ProductFactory();
    private final CustomerFactory customerFactory = new CustomerFactory();
    private final FontAwesomeIcon closeIcon = new FontAwesomeIcon();

    public InvoiceFactory() {
    }

    public void showProductPopup(Node node, Node vBox) throws IOException, SQLException {
        Double x, y;
        PopOver popOver = new PopOver();
        popOver.setTitle("Add Item to Invoice");
        popOver.setContentNode(vBox);
        popOver.setPrefSize(500, 600);
//        popOver.setDetachable(false);
        x = node.getScene().getWidth() / 3;
        y = node.getScene().getHeight() / 3;
        popOver.show(node, x, y, Duration.millis(300));
    }

    public void showCustomerPopup(Node node, Node vBox) {
        Double x, y;
        PopOver popOver = new PopOver();
        popOver.setTitle("Select Customer");
        popOver.setContentNode(vBox);
        popOver.setPrefSize(500, 600);
//        popOver.setDetachable(false);
        x = node.getScene().getWidth() / 3;
        y = node.getScene().getHeight() / 3;
        popOver.show(node, x, y, Duration.millis(300));
    }

    public void setProductTable(
            TableView<Product> tableView,
            TableColumn<Product, String> colCode,
            TableColumn<Product, String> colName,
            TableColumn<Product, Integer> colQty,
            TableColumn<Product, Double> colUnit,
            TableColumn<Product, Double> colCost,
            TextField searchField
    ) throws SQLException {
        closeIcon.setIcon(FontAwesomeIconName.CLOSE);
        productFactory.tableViewProducts(
                tableView,
                colCode,
                colName,
                colQty,
                colUnit,
                colCost
        );

        productFactory.searchProductTable(tableView, searchField, closeIcon);

    }

    public void setProductCellFactory
            (
                    TableColumn<Invoice, String> colCode,
                    TableColumn<Invoice, String> colName,
                    TableColumn<Invoice, Double> colUnit,
                    TableColumn<Invoice, Integer> colQty,
                    TableColumn<Invoice, Double> colDiscount,
                    TableColumn<Invoice, Double> colTotal
            ) {

        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

    }

    public void setCustomerTable(
            TableView<Customer> tableView,
            TableColumn<Customer, String> colName,
            TableColumn<Customer, String> colAddress,
            TableColumn<Customer, String> colPhone,
            TableColumn<Customer, Double> colCredit,
            TableColumn<Customer, Double> colDebit,
            TextField searchField
    ) throws SQLException {
        closeIcon.setIcon(FontAwesomeIconName.CLOSE);
        customerFactory.tableViewCustomers(
                tableView, colName, colAddress, colPhone, colCredit, colDebit
        );

        customerFactory.searchCustomerTable(tableView, searchField, closeIcon);

    }

    public void setCustomerCellFactory
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

}


//try {
//        ResultSet resultSet = DBConnection.dbExecuteQuery(
//        CustomerQuery.getCustomers()
//        );
//        while (resultSet.next()) {
//        customerList.add(
//        new Customer(
//        resultSet.getString(Const.CUSTOMER_NAME),
//        resultSet.getString(Const.CUSTOMER_PHONE),
//        resultSet.getString(Const.CUSTOMER_ADDRESS),
//        resultSet.getDouble(Const.CUSTOMER_CREDIT_LIMIT),
//        resultSet.getDouble(Const.CUSTOMER_DEBIT_LIMIT)
//        )
//        );
//        }
//        } catch (SQLException throwables) {
//        throwables.printStackTrace();
//        }