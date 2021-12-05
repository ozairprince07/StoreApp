package controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import factory.ProductFactory;
import helper.PathHelper;
import helper.ViewHelper;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Product;
import org.controlsfx.control.PopOver;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ExploreController implements Initializable {

    // * Explore Products Tab Data
    public TableView<Product> productTableView;
    public TableColumn<Product, String> codeCol, nameCol;
    public TableColumn<Product, Double> unitCol, costCol;
    public TableColumn<Product, Integer> qtyCol;

    public TextField searchField;
    public Label totalProductLabel;

    public FontAwesomeIcon closeIcon;
    public Button refreshBtn, exportBtn, filterBtn;
    public CheckBox selectAllCheck;

    // * Add Products Tab Data
    public TextField codeField, nameField, unitField, costField, qtyField;
    public Button saveBtn, clearBtn;

    ProductFactory productFactory = new ProductFactory();
    ViewHelper viewHelper = new ViewHelper();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        closeIcon.setVisible(false);

        // * Explore Products Tab Initialization

        try {
            productFactory.tableViewProducts(
                    productTableView, codeCol, nameCol, qtyCol, unitCol, costCol
            );

            totalProductLabel.setText("Total Products : " + productFactory.getTotalProducts());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        // * Set Context Menu to Table View
        productFactory.setContextMenu(productTableView);

        refreshBtn.setOnAction(e -> {
            try {
                productFactory.refreshProductTable(
                        productTableView, codeCol, nameCol, qtyCol, unitCol, costCol
                );

                totalProductLabel.setText("Total Products : " + productFactory.getTotalProducts());

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        // * Search Products Table
        searchField.setOnKeyPressed(e -> {
            productFactory.searchProductTable(productTableView, searchField, closeIcon);
        });

        closeIcon.setOnMouseClicked(e -> {
            searchField.clear();
        });

        // * Export Product Table
        exportBtn.setOnAction(e -> {
            try {
                productFactory.excelExport(productTableView);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

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

    public void selectAll(ActionEvent event) {
        if (!selectAllCheck.isSelected()) {
            selectAllCheck.setSelected(false);
            productTableView.getSelectionModel().clearSelection();
        } else {
            selectAllCheck.setSelected(true);
            productTableView.getSelectionModel().selectAll();
        }
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

    // ? Show Popover for Filter
    private void showFilterPopover(Button btn) throws IOException {
        PopOver popOver = new PopOver();
        popOver.setTitle("Filter Product");
        popOver.setArrowLocation(PopOver.ArrowLocation.TOP_CENTER);
        viewHelper.loadPopupFiles(popOver, PathHelper.PRODUCT_FILTER_FILE);
        popOver.setMinSize(200, 400);
        popOver.show(btn);
    }


}
