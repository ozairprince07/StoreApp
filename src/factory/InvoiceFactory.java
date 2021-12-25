package factory;

import controller.AddItemController;
import customWidgets.InputFilter;
import database.Const;
import database.DBConnection;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconName;
import helper.PathHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import model.Product;
import org.controlsfx.control.PopOver;
import query.CustomerQuery;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceFactory {

    private final ObservableList<String> customerList = FXCollections.observableArrayList();
    private final FilteredList<String> filteredList = new FilteredList<>(customerList);
    private final ProductFactory productFactory = new ProductFactory();
    private final FontAwesomeIcon closeIcon = new FontAwesomeIcon();

    Product selectedProduct;
    AddItemController addItemController;

    public InvoiceFactory() {
    }

    public void setCustomerBox(ComboBox<String> customerBox) {

        try {
            ResultSet resultSet = DBConnection.dbExecuteQuery(
                    CustomerQuery.getCustomers()
            );
            while (resultSet.next()) {
                customerList.add(
                        resultSet.getString(Const.CUSTOMER_NAME)
                );
//                customerList.add(
//                        new Customer(
//                            resultSet.getString(Const.CUSTOMER_NAME),
//                            resultSet.getString(Const.CUSTOMER_PHONE),
//                            resultSet.getString(Const.CUSTOMER_ADDRESS),
//                            resultSet.getDouble(Const.CUSTOMER_CREDIT_LIMIT),
//                            resultSet.getDouble(Const.CUSTOMER_DEBIT_LIMIT)
//                        )
//                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        customerBox.getItems().clear();
        customerBox.getItems().addAll(customerList);
        customerBox.getEditor().textProperty().addListener(
                new InputFilter<String>(customerBox, filteredList, false)
        );
        customerBox.setItems(filteredList);
    }

    public void showPopover(Node node) throws IOException {
        Parent root;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(PathHelper.ADD_ITEM_FILE));
        Double x, y;
        root = loader.load();
        PopOver popOver = new PopOver();
        popOver.setTitle("Add Item to Invoice");
        popOver.setContentNode(root);
//        popOver.setDetachable(false);
        x = node.getScene().getWidth() / 3;
        y = node.getScene().getHeight() / 3;
        popOver.show(node, x, y, Duration.millis(300));
    }

    public void setTable(
            TableView<Product> tableView,
            TableColumn<Product, String> colCode,
            TableColumn<Product, String> colName,
            TableColumn<Product, Double> colUnit,
            TableColumn<Product, Double> colCost,
            TableColumn<Product, Integer> colQty,
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

}
