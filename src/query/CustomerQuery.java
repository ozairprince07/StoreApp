package query;

import database.Const;

public class CustomerQuery {

    public static String getCustomers() {
        return "SELECT * FROM " + Const.TABLE_CUSTOMER;
    }

    public static String insertCustomer(
            String name,
            String address,
            String phone,
            String whatsapp,
            double creditLimit,
            double debitLimit
    ) {
        return "INSERT INTO " + Const.TABLE_CUSTOMER +
                " (" + Const.CUSTOMER_NAME + ", " +
                Const.CUSTOMER_ADDRESS + ", " +
                Const.CUSTOMER_PHONE + ", " +
                Const.CUSTOMER_WHATSAPP + ", " +
                Const.CUSTOMER_CREDIT_LIMIT + ", " +
                Const.CUSTOMER_DEBIT_LIMIT +
                ") VALUES ('" + name + "', '" + address + "', '" + phone + "', '" + whatsapp + "', " + creditLimit + ", " + debitLimit + ") ";
    }

    public static String deleteCustomer(int customerId) {
        return "DELETE FROM " + Const.TABLE_CUSTOMER +
                " WHERE " + Const.CUSTOMER_ID + " = " + customerId + " ";
    }

    public static String getCustomerId(String phone) {
        return "SELECT " + Const.CUSTOMER_ID +
                " FROM " + Const.TABLE_CUSTOMER +
                " WHERE " + Const.CUSTOMER_PHONE + " = '" + phone + "' ";
    }

}
