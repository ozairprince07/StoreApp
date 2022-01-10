package query;

import database.Const;

public class InvoiceItemQuery {

    public static String insertInvoiceItem(
            int invoice_no,
            String item_name,
            String item_code,
            int item_qty,
            double item_costPrice,
            double item_unitPrice,
            double item_discount,
            double item_total
    ) {
        return "INSERT INTO " + Const.TABLE_INVOICE_ITEM +
                " (" + Const.INVOICE_ITEM_INVOICE_NO +
                ", " + Const.INVOICE_ITEM_NAME +
                ", " + Const.INVOICE_ITEM_CODE +
                ", " + Const.INVOICE_ITEM_QTY +
                ", " + Const.INVOICE_ITEM_COST_PRICE +
                ", " + Const.INVOICE_ITEM_UNIT_PRICE +
                ", " + Const.INVOICE_ITEM_DISCOUNT +
                ", " + Const.INVOICE_ITEM_TOTAL_PRICE +
                ") VALUES (" + invoice_no + ", '" + item_name + "', '" + item_code + "', '" + item_qty + "', '" + item_costPrice + "', '" + item_unitPrice + "', '" + item_discount + "', '" + item_total + "' ";
    }

}
