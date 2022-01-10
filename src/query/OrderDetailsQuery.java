package query;

import database.Const;

public class OrderDetailsQuery {

    public static String insetOrderDetails(int order_id, int product_id, int qty, double unit_price, double discount, double total_price) {
        return "INSERT INTO " + Const.TABLE_ORDER_DETAILS +
                " (" + Const.ORDER_DETAILS_ORDER_ID +
                ", " + Const.ORDER_DETAILS_PRODUCT_ID +
                ", " + Const.ORDER_DETAILS_QTY +
                ", " + Const.ORDER_DETAILS_UNIT_PRICE +
                ", " + Const.ORDER_DETAILS_DISCOUNT +
                ", " + Const.ORDER_DETAILS_TOTAL_PRICE +
                ") VALUES (" + order_id + ", " + product_id + ", " + qty + ", " + unit_price + ", " + discount + ", " + total_price + ") ";
    }

}
