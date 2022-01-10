package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class OrderDetails {

    private final IntegerProperty orderId;
    private final IntegerProperty productId;
    private final IntegerProperty qty;
    private final DoubleProperty unitPrice;
    private final DoubleProperty discount;
    private final DoubleProperty totalPrice;

    public OrderDetails(int orderId, int productId, int qty, double unitPrice, double discount, double totalPrice) {
        this.orderId = new SimpleIntegerProperty(orderId);
        this.productId = new SimpleIntegerProperty(productId);
        this.qty = new SimpleIntegerProperty(qty);
        this.unitPrice = new SimpleDoubleProperty(unitPrice);
        this.discount = new SimpleDoubleProperty(discount);
        this.totalPrice = new SimpleDoubleProperty(totalPrice);
    }

    public int getOrderId() {
        return orderId.get();
    }

    public void setOrderId(int orderId) {
        this.orderId.set(orderId);
    }

    public IntegerProperty orderIdProperty() {
        return orderId;
    }

    public int getProductId() {
        return productId.get();
    }

    public void setProductId(int productId) {
        this.productId.set(productId);
    }

    public IntegerProperty productIdProperty() {
        return productId;
    }

    public int getQty() {
        return qty.get();
    }

    public void setQty(int qty) {
        this.qty.set(qty);
    }

    public IntegerProperty qtyProperty() {
        return qty;
    }

    public double getUnitPrice() {
        return unitPrice.get();
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice.set(unitPrice);
    }

    public DoubleProperty unitPriceProperty() {
        return unitPrice;
    }

    public double getDiscount() {
        return discount.get();
    }

    public void setDiscount(double discount) {
        this.discount.set(discount);
    }

    public DoubleProperty discountProperty() {
        return discount;
    }

    public double getTotalPrice() {
        return totalPrice.get();
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice.set(totalPrice);
    }

    public DoubleProperty totalPriceProperty() {
        return totalPrice;
    }

}
