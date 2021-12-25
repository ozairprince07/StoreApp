package factory;

import database.Const;
import database.DBConnection;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconName;
import helper.ExportHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Product;
import query.ProductQuery;

import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ProductFactory {

    private final ObservableList<Product> productList = FXCollections.observableArrayList();
    private final ExportHelper<Product> exportHelper = new ExportHelper<>();
    private int product_id;
    private int p_counter;
    private ResultSet resultSet;

    public ProductFactory() {
    }

    public void addProduct(TextField code, TextField name, TextField unitPrice, TextField costPrice, TextField qty, Blob image, int supplier_id) throws SQLException {
        DBConnection.dbExecute(
                ProductQuery.insertProduct(
                        code.getText().trim(),
                        name.getText().trim(),
                        Double.parseDouble(unitPrice.getText().trim()),
                        Double.parseDouble(costPrice.getText().trim()),
                        Integer.parseInt(qty.getText().trim()),
                        image
                )
        );

        resultSet = DBConnection.dbExecuteQuery(
                ProductQuery.getProductId(code.getText().trim())
        );

        while (resultSet.next()) {
            product_id = resultSet.getInt(Const.PRODUCT_ID);
        }

        DBConnection.dbExecute(
                ProductQuery.insertProductDetails(product_id, supplier_id, LocalDateTime.now())
        );

    }

    public void tableViewProducts
            (
                    TableView<Product> tableView,
                    TableColumn<Product, String> colCode,
                    TableColumn<Product, String> colName,
                    TableColumn<Product, Integer> colQty,
                    TableColumn<Product, Double> colUnit,
                    TableColumn<Product, Double> colCost
            ) throws SQLException {

        refreshProductTable(tableView, colCode, colName, colQty, colUnit, colCost);

    }

    public void refreshProductTable
            (
                    TableView<Product> tableView,
                    TableColumn<Product, String> colCode,
                    TableColumn<Product, String> colName,
                    TableColumn<Product, Integer> colQty,
                    TableColumn<Product, Double> colUnit,
                    TableColumn<Product, Double> colCost
            ) throws SQLException {

        // * CLear the Product Observable List
        productList.clear();

        // * Get the data from database
        resultSet = DBConnection.dbExecuteQuery(
                ProductQuery.getProducts()
        );

        p_counter = 0;

        // * Set data to Product Observable List Model
        while (resultSet.next()) {
            p_counter++;
            productList.add(
                    new Product(
                            resultSet.getString(Const.PRODUCT_CODE),
                            resultSet.getString(Const.PRODUCT_CODE),
                            resultSet.getDouble(Const.PRODUCT_UNIT_PRICE),
                            resultSet.getDouble(Const.PRODUCT_COST_PRICE),
                            resultSet.getInt(Const.PRODUCT_QUANTITY),
                            null
                    )
            );
            // * Set data to Tableview
            tableView.setItems(productList);
        }

        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        cellFactory(colCode, colName, colQty, colUnit, colCost);

    }

    public void deleteProduct(String code) throws SQLException {

        // * Get product id of selected Item
        resultSet = DBConnection.dbExecuteQuery(
                ProductQuery.getProductId(code)
        );

        while (resultSet.next()) {
            product_id = resultSet.getInt(Const.PRODUCT_ID);
        }

        // * Delete Product with specific product id
        DBConnection.dbExecute(
                ProductQuery.deleteProduct(
                        product_id
                )
        );

    }

    public String getTotalProducts() {
        return String.valueOf(p_counter);
    }

    private void cellFactory
            (
                    TableColumn<Product, String> colCode,
                    TableColumn<Product, String> colName,
                    TableColumn<Product, Integer> colQty,
                    TableColumn<Product, Double> colUnit,
                    TableColumn<Product, Double> colCost
            ) {

        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colUnit.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("costPrice"));

    }

    public void setContextMenu(TableView<Product> tableView) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem editItem = new MenuItem(), deleteItem = new MenuItem();
        FontAwesomeIcon editIcon = new FontAwesomeIcon(), deleteIcon = new FontAwesomeIcon();
        contextMenu.setAutoHide(true);
        Label editText = new Label("Edit"), deleteText = new Label("Delete");
        editIcon.setGlyphStyleClass("glyph-icon");
        deleteIcon.setGlyphStyleClass("glyph-icon");
        editIcon.setIcon(FontAwesomeIconName.EDIT);
        deleteIcon.setIcon(FontAwesomeIconName.TRASH);
        editText.setGraphic(editIcon);
        deleteText.setGraphic(deleteIcon);
        editText.setGraphicTextGap(20);
        deleteText.setGraphicTextGap(20);
        editItem.setGraphic(editText);
        deleteItem.setGraphic(deleteText);
        contextMenu.getItems().addAll(editItem, deleteItem);
        tableView.setOnContextMenuRequested(e -> {
            double x = e.getScreenX();
            double y = e.getScreenY();
            contextMenu.show(tableView, x, y);
        });

        // * Set Actions to Context Menu Items
        editItem.setOnAction(e -> {

        });

        deleteItem.setOnAction(e -> {
            Product product = tableView.getSelectionModel().getSelectedItem();
            try {
                deleteProduct(product.getCode());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });


    }

    public void searchProductTable(TableView<Product> tableView, TextField searchField, FontAwesomeIcon icon) {
        FilteredList<Product> filteredList = new FilteredList<>(productList);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(productModel -> {
                // * Check if the value of search field is to be null
                if (newValue.isBlank() || newValue.isEmpty() || newValue.equals("null")) {
                    icon.setVisible(false);
                    return true;
                }

                String keyword = newValue.toLowerCase();

                if (productModel.getCode().toLowerCase().contains(keyword)) {
                    icon.setVisible(true);
                    return true;
                } else if (productModel.getName().toLowerCase().contains(keyword)) {
                    icon.setVisible(true);
                    return true;
                } else if (String.valueOf(productModel.getQuantity()).toLowerCase().contains(keyword)) {
                    icon.setVisible(true);
                    return true;
                } else if (String.valueOf(productModel.getUnitPrice()).toLowerCase().contains(keyword)) {
                    icon.setVisible(true);
                    return true;
                } else if (String.valueOf(productModel.getCostPrice()).toLowerCase().contains(keyword)) {
                    icon.setVisible(true);
                    return true;
                } else {
                    icon.setVisible(true);
                    return false;
                }

            });
        });

        SortedList<Product> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);

    }

    public void excelExport(TableView<Product> tableView) throws IOException {
        exportHelper.excelExport(tableView);
    }

//    private void addButtonToTableView(TableView<Product> tableView) {
//        TableColumn<Product, Void> actionCol = new TableColumn<>("Actions");
//
//        Callback<TableColumn<Product, Void>, TableCell<Product, Void>> cellFactory = new Callback<TableColumn<Product, Void>, TableCell<Product, Void>>() {
//            @Override
//            public TableCell<Product, Void> call(TableColumn<Product, Void> productVoidTableColumn) {
//                final TableCell<Product, Void> cell = new TableCell<Product, Void>() {
//                    private final HBox actionBox = new HBox();
//                    private final Button delBtn = new Button("DELETE");
//                    private final Button editBtn = new Button("EDIT");
//                    private final Button settingBtn = new Button("SETTING");
//                    private final FontAwesomeIcon delIcon = new FontAwesomeIcon();
//                    private final FontAwesomeIcon editIcon = new FontAwesomeIcon();
//                    private final FontAwesomeIcon gearIcon = new FontAwesomeIcon();
//
//                    // * Add Actions for Button here
//
//                    {
//                        // * Delete Button Action
//                        delBtn.setOnAction(e -> {
//                            Product model = tableView.getSelectionModel().getSelectedItem();
//                            try {
//                                deleteProduct(model.getCode());
//                            } catch (SQLException throwables) {
//                                throwables.printStackTrace();
//                            }
//                        });
//
//                    }
//
//                    // * Update Cell Item
//                    @Override
//                    protected void updateItem(Void item, boolean empty) {
//                        super.updateItem(item, empty);
//                        if (empty) {
//                            setGraphic(null);
//                        } else {
//
//                            for (Node child : actionBox.getChildren())  actionBox.getChildren().remove(child);
//
//                            actionBox.setPrefSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
//                            actionBox.setSpacing(5);
//                            actionBox.setAlignment(Pos.CENTER);
//                            actionBox.setBackground(null);
//
//                            delIcon.setGlyphStyleClass("glyph-icon");
//                            delIcon.setIcon(FontAwesomeIconName.TRASH);
//                            delBtn.setGraphic(delIcon);
//                            delBtn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
//
//                            editIcon.setGlyphStyleClass("glyph-icon");
//                            editIcon.setIcon(FontAwesomeIconName.EDIT);
//                            editBtn.setGraphic(editIcon);
//                            editBtn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
//
//                            gearIcon.setGlyphStyleClass("glyph-icon");
//                            gearIcon.setIcon(FontAwesomeIconName.GEAR);
//                            settingBtn.setGraphic(gearIcon);
//                            settingBtn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
//
//                            actionBox.getChildren().addAll(delBtn, editBtn, settingBtn);
//                            setAlignment(Pos.CENTER);
//                            setGraphic(actionBox);
//                        }
//                    }
//                };
//                return cell;
//            }
//        };
//
//        actionCol.setCellFactory(cellFactory);
//        tableView.getColumns().add(actionCol);
//
//    }


}
