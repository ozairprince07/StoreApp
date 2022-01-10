package model;

import database.Const;
import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import query.CustomerQuery;
import query.OrderDetailsQuery;
import query.OrderQuery;
import query.ProductQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class OrderFactory {

    ObservableList<Order> orderList = FXCollections.observableArrayList();
    ResultSet resultSet;
    LocalDateTime localDateTime;

    public void insetIntoOrder(Customer selectedCustomer) throws SQLException {
        DBConnection.dbExecute(
                OrderQuery.insertOrder(
                        getCustomerId(selectedCustomer.getPhone()),
                        localDateTime = LocalDateTime.now()
                )
        );
    }

    public void insertIntoOrderDetails(ObservableList<Invoice> invoiceList) throws SQLException {
        for (Invoice invoice : invoiceList) {
            DBConnection.dbExecute(
                    OrderDetailsQuery.insetOrderDetails(
                            getOrderId(),
                            getProductId(invoice.getCode()),
                            invoice.getQty(),
                            invoice.getUnit(),
                            invoice.getDiscount(),
                            invoice.getTotal()
                    )
            );
        }
    }

    private int getCustomerId(String customerPhone) throws SQLException {
        resultSet = DBConnection.dbExecuteQuery(CustomerQuery.getCustomerId(customerPhone));
        int customerId = 0;
        while (resultSet.next()) {
            customerId = resultSet.getInt(Const.CUSTOMER_ID);
        }
        return customerId;
    }

    private int getOrderId() throws SQLException {
        resultSet = DBConnection.dbExecuteQuery(OrderQuery.getLastId());
        int order_id = 0;
        while (resultSet.next()) {
            order_id = resultSet.getInt(Const.ORDER_ID);
        }
        return order_id;
    }

    private int getProductId(String code) throws SQLException {
        resultSet = DBConnection.dbExecuteQuery(ProductQuery.getProductId(code));
        int prduct_id = 0;
        while (resultSet.next()) {
            prduct_id = resultSet.getInt(Const.PRODUCT_ID);
        }
        return prduct_id;
    }

}
