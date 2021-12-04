package model;

import javafx.beans.property.*;

import java.sql.Blob;

public class Product {

    private final StringProperty code;
    private final StringProperty name;
    private final DoubleProperty unitPrice;
    private final DoubleProperty costPrice;
    private final IntegerProperty quantity;
    private Blob image;

    public Product(String code, String name, double unitPrice, double costPrice, int quantity, Blob image) {
        this.code = new SimpleStringProperty(code);
        this.name = new SimpleStringProperty(name);
        this.unitPrice = new SimpleDoubleProperty(unitPrice);
        this.costPrice = new SimpleDoubleProperty(costPrice);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.image = image;
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

    public double getUnitPrice() {
        return unitPrice.get();
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice.set(unitPrice);
    }

    public DoubleProperty unitPriceProperty() {
        return unitPrice;
    }

    public double getCostPrice() {
        return costPrice.get();
    }

    public void setCostPrice(double costPrice) {
        this.costPrice.set(costPrice);
    }

    public DoubleProperty costPriceProperty() {
        return costPrice;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

}
