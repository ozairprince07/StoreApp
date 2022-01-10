package model;

import javafx.beans.property.*;

public class Invoice {

    private final StringProperty code, name;
    private final IntegerProperty qty;
    private final DoubleProperty unit, discount, total;

    public Invoice(String code, String name, int qty, double unit, double discount, double total) {
        this.code = new SimpleStringProperty(code);
        this.name = new SimpleStringProperty(name);
        this.qty = new SimpleIntegerProperty(qty);
        this.unit = new SimpleDoubleProperty(unit);
        this.discount = new SimpleDoubleProperty(discount);
        this.total = new SimpleDoubleProperty(total);
    }

    public String getCode() {
        return code.get();
    }

    public void setCode(String code) {
        this.code.set(code);
    }

    public StringProperty codeProperty() {
        return code;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
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

    public double getUnit() {
        return unit.get();
    }

    public void setUnit(double unit) {
        this.unit.set(unit);
    }

    public DoubleProperty unitProperty() {
        return unit;
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

    public double getTotal() {
        return total.get();
    }

    public void setTotal(double total) {
        this.total.set(total);
    }

    public DoubleProperty totalProperty() {
        return total;
    }
}
