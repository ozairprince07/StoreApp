package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Customer {

    private final StringProperty name;
    private final StringProperty address;
    private final StringProperty phone;
    private final DoubleProperty creditLimit;
    private final DoubleProperty debitLimit;

    public Customer(String name, String address, String phone, double creditLimit, double debitLimit) {
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.phone = new SimpleStringProperty(phone);
        this.creditLimit = new SimpleDoubleProperty(creditLimit);
        this.debitLimit = new SimpleDoubleProperty(debitLimit);
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

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public StringProperty addressProperty() {
        return address;
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public double getCreditLimit() {
        return creditLimit.get();
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit.set(creditLimit);
    }

    public DoubleProperty creditLimitProperty() {
        return creditLimit;
    }

    public double getDebitLimit() {
        return debitLimit.get();
    }

    public void setDebitLimit(double debitLimit) {
        this.debitLimit.set(debitLimit);
    }

    public DoubleProperty debitLimitProperty() {
        return debitLimit;
    }

}
