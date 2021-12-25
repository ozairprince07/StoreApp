package database;

public class Const {

    // * === *** === *** === *** === *** === *** === *** === *** === *** */
    /* ! <=> === *** <=> === *** Tables Name *** <=> === *** <=> === */
    // * === *** === *** === *** === *** === *** === *** === *** === *** */

    public static String TABLE_COMPANY = "Company";
    public static String TABLE_SUPPLIER = "Supplier";
    public static String TABLE_PRODUCT = "Product";
    public static String TABLE_PRODUCT_DETAILS = "Product_Details";
    public static String TABLE_CUSTOMER = "Customer";
    public static String TABLE_ORDER = "Order";
    public static String TABLE_ORDER_DETAILS = "Order_Details";
    public static String TABLE_INVOICE = "Invoice";
    public static String TABLE_INVOICE_ITEM = "Invoice_Item";

    // * === *** === *** === *** === *** === *** === *** === *** === *** */
    /* ! <=> === *** <=> === *** Column Names *** <=> === *** <=> === */
    // * === *** === *** === *** === *** === *** === *** === *** === *** */

    /* ? -> -> -> -> -> Company Columns <- <- <- <- <- */
    public static String COMPANY_ID = "id";
    public static String COMPANY_NAME = "name";
    public static String COMPANY_PHONE = "phone";
    public static String COMPANY_ADDRESS = "address";

    /* ? -> -> -> -> -> Columns Supplier <- <- <- <- <- */
    public static String SUPPLIER_ID = "id";
    public static String SUPPLIER_COMPANY_ID = "company_id";
    public static String SUPPLIER_NAME = "name";
    public static String SUPPLIER_PHONE = "phone";
    public static String SUPPLIER_ADDRESS = "address";

    /* ? -> -> -> -> -> Columns Supplier <- <- <- <- <- */
    public static String PRODUCT_ID = "product_id";
    public static String PRODUCT_CODE = "code";
    public static String PRODUCT_NAME = "name";
    public static String PRODUCT_UNIT_PRICE = "unit_price";
    public static String PRODUCT_COST_PRICE = "purchasing_price";
    public static String PRODUCT_QUANTITY = "quantity";
    public static String PRODUCT_IMAGE = "image";

    /* ? -> -> -> -> -> Columns Product Details <- <- <- <- <- */
    public static String PRODUCT_DETAILS_PRODUCT_ID = "product_id";
    public static String PRODUCT_DETAILS_SUPPLIER_ID = "supplier_id";
    public static String PRODUCT_DETAILS_DATE_CREATED = "date_created";

    /* ? -> -> -> -> -> Columns Customer <- <- <- <- <- */
    public static String CUSTOMER_ID = "customer_id";
    public static String CUSTOMER_NAME = "name";
    public static String CUSTOMER_ADDRESS = "address";
    public static String CUSTOMER_PHONE = "phone";
    public static String CUSTOMER_WHATSAPP = "whatsapp";
    public static String CUSTOMER_CREDIT_LIMIT = "credit_limit";
    public static String CUSTOMER_DEBIT_LIMIT = "debit_limit";

    /* ? -> -> -> -> -> Columns Order <- <- <- <- <- */
    public static String ORDER_ID = "order-id";
    public static String ORDER_CUSTOMER_ID = "customer_id";
    public static String ORDER_DATE_CREATED = "date_created";

    /* ? -> -> -> -> -> Columns Order Details <- <- <- <- <- */
    public static String ORDER_DETAILS_ITEM_ID = "order_item_id";
    public static String ORDER_DETAILS_ORDER_ID = "order_id";
    public static String ORDER_DETAILS_PRODUCT_ID = "product_id";
    public static String ORDER_DETAILS_QTY = "qty";
    public static String ORDER_DETAILS_UNIT_PRICE = "unit_price";
    public static String ORDER_DETAILS_DISCOUNT = "discount";
    public static String ORDER_DETAILS_TOTAL_PRICE = "total_price";

    /* ? -> -> -> -> -> Columns Invoice <- <- <- <- <- */
    public static String INVOICE_NO = "invoice_no";
    public static String INVOICE_CUSTOMER_ID = "customer_id";
    public static String INVOICE_DATE_CREATED = "date_created";
    public static String INVOICE_NET_DISCOUNT = "net_Discount";
    public static String INVOICE_NET_TOTAL = "net_total";

    /* ? -> -> -> -> -> Columns Invoice Item <- <- <- <- <- */
    public static String INVOICE_ITEM_ID = "invoice_item_id";
    public static String INVOICE_ITEM_INVOICE_NO = "invoice_no";
    public static String INVOICE_ITEM_NAME = "item_name";
    public static String INVOICE_ITEM_CODE = "item_code";
    public static String INVOICE_ITEM_QTY = "item_qty";
    public static String INVOICE_ITEM_COST_PRICE = "item_cost_price";
    public static String INVOICE_ITEM_UNIT_PRICE = "item_unit_price";
    public static String INVOICE_ITEM_DISCOUNT = "item_discount";
    public static String INVOICE_ITEM_TOTAL_PRICE = "item_total_price";

}
