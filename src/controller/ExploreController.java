package controller;

import factory.ProductFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Product;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ExploreController implements Initializable {

    // * Explore Products Tab Data
    public TableView<Product> tableView;
    public TableColumn<Product, String> codeCol, nameCol;
    public TableColumn<Product, Double> unitCol, costCol;
    public TableColumn<Product, Integer> qtyCol;

    // * Add Products Tab Data
    public TextField codeField, nameField, unitField, costField, qtyField;
    public Button saveBtn, clearBtn;

    ProductFactory productFactory = new ProductFactory();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // * Add Products Tab Initialization

        saveBtn.setOnAction(e -> {
            try {
                productFactory.addProduct(codeField, nameField, unitField, costField, qtyField, null, 19);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            clearFields();
        });

        clearBtn.setOnAction(e -> {
            clearFields();
        });

    }

    // ? Method to clear fields
    private void clearFields() {
        codeField.clear();
        nameField.clear();
        unitField.clear();
        costField.clear();
        qtyField.clear();
    }

    // ? Verify fields

}
