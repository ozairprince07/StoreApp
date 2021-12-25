package controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconName;
import factory.InvoiceFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Product;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddItemController implements Initializable {

    public TableView<Product> tableView;
    public TableColumn<Product, String> colCode, colName;
    public TableColumn<Product, Double> colUnit, colCost;
    public TableColumn<Product, Integer> colQty;
    public TextField searchField;

    InvoiceFactory invoiceFactory = new InvoiceFactory();
    // * Context Menu
    ContextMenu contextMenu = new ContextMenu();
    MenuItem addItem = new MenuItem();
    Label addLabel = new Label("Add Item");
    FontAwesomeIcon addIcon = new FontAwesomeIcon();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            invoiceFactory.setTable(
                    tableView,
                    colCode,
                    colName,
                    colUnit,
                    colCost,
                    colQty,
                    searchField
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        setContextMenu(tableView);

    }

    public void setContextMenu(TableView<Product> tableView) {
        addIcon.setIcon(FontAwesomeIconName.PLUS_SQUARE);
        addIcon.setGlyphStyleClass("glyph-icon");
        addLabel.setGraphic(addIcon);
        addLabel.setGraphicTextGap(20);

        addItem.setGraphic(addLabel);

        contextMenu.getItems().add(addItem);

        tableView.setOnContextMenuRequested(e -> {
            double x = e.getScreenX();
            double y = e.getScreenY();
            contextMenu.show(tableView.getScene().getWindow(), x, y);
        });

    }

    public MenuItem getAddItem() {
        return addItem;
    }

    public void setAddItem(MenuItem addItem) {
        this.addItem = addItem;
    }
}

