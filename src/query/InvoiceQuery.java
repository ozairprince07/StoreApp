package query;

import database.Const;

import java.time.LocalDateTime;

public class InvoiceQuery {

    public static String insertInvoice(
            int customer_id,
            LocalDateTime dateTime,
            double netDiscount,
            double netTotal
    ) {

        return "INSERT INTO " + Const.TABLE_INVOICE +
                " (" + Const.INVOICE_CUSTOMER_ID +
                ", " + Const.INVOICE_DATE_CREATED +
                ", " + Const.INVOICE_NET_DISCOUNT +
                ", " + Const.INVOICE_NET_TOTAL +
                ") VALUES (" + customer_id + ", " + dateTime + ", " + netDiscount + ", " + netTotal + " ";

    }

    public static String getLastId() {
        return "SELECT " + Const.INVOICE_NO +
                " FROM " + Const.TABLE_INVOICE +
                " ORDER BY " + Const.INVOICE_NO +
                " DESC LIMIT 1";
    }

}
