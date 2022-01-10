package query;

import database.Const;

import java.time.LocalDateTime;

public class OrderQuery {

    public static String insertOrder(int customer_id, LocalDateTime dateTime) {
        return "INSERT INTO `" + Const.TABLE_ORDER + "`( " +
                Const.ORDER_CUSTOMER_ID + ", " +
                Const.ORDER_DATE_CREATED + ") VALUES (" + customer_id + ", '" + dateTime + "')";
    }

    public static String getOrderId(LocalDateTime dateTime) {
        return "SELECT " + Const.ORDER_DATE_CREATED +
                " FROM `" + Const.TABLE_ORDER + "` WHERE " + Const.ORDER_DATE_CREATED + " = '" + dateTime + "' ";
    }

    public static String getLastId() {
        return "SELECT " + Const.ORDER_ID +
                " FROM `" + Const.TABLE_ORDER + "` ORDER BY " +
                Const.ORDER_ID + " DESC LIMIT 1";
    }

}
