package factory;

import database.Const;
import database.DBConnection;
import javafx.scene.control.TextField;
import query.ProductQuery;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ProductFactory {

    int product_id;
    ResultSet resultSet;

    public ProductFactory() {
    }

    public void addProduct(TextField code, TextField name, TextField unitPrice, TextField costPrice, TextField qty, Blob image, int supplier_id) throws SQLException {
        DBConnection.dbExecute(
                ProductQuery.insertProduct(
                        code.getText().trim(),
                        name.getText().trim(),
                        Double.parseDouble(unitPrice.getText()),
                        Double.parseDouble(costPrice.getText()),
                        Integer.parseInt(qty.getText()),
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

}
