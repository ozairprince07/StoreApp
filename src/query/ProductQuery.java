package query;

import database.Const;

import java.sql.Blob;
import java.time.LocalDateTime;

public class ProductQuery {

    // * Product Query

    public static String getProducts() {
        return "SELECT * FROM " + Const.TABLE_PRODUCT;
    }

    public static String insertProduct(
            String code,
            String name,
            double unit_price,
            double cost_price,
            int qty,
            Blob image
    ) {
        return "INSERT INTO " + Const.TABLE_PRODUCT +
                " (" + Const.PRODUCT_CODE + ", " +
                Const.PRODUCT_NAME + ", " +
                Const.PRODUCT_UNIT_PRICE + ", " +
                Const.PRODUCT_COST_PRICE + " ," +
                Const.PRODUCT_QUANTITY + ", " +
                Const.PRODUCT_IMAGE +
                ") VALUES ('" + code + "', '" + name + "', " + unit_price + ", " + cost_price + ", " + qty + ", '" + image + "') ";
    }

    public static String getProductId(String code) {
        return "SELECT " + Const.PRODUCT_ID +
                " FROM " + Const.TABLE_PRODUCT +
                " WHERE " + Const.PRODUCT_CODE +
                " = '" + code + "' ";
    }

    // * Product Details Query

    public static String insertProductDetails(int product_id, int supplier_id, LocalDateTime dateTime) {
        return "INSERT INTO " + Const.TABLE_PRODUCT_DETAILS +
                " (" + Const.PRODUCT_DETAILS_PRODUCT_ID + ", " +
                Const.PRODUCT_DETAILS_SUPPLIER_ID + ", " +
                Const.PRODUCT_DETAILS_DATE_CREATED + ") VALUES ('" + product_id + "', '" + supplier_id + "', '" + dateTime + "') ";
    }

}
