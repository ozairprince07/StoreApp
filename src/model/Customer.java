package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Customer {

    private final StringProperty name;
    private final StringProperty address;
    private final StringProperty phone;
    private final DoubleProperty credit;
    private final DoubleProperty debit;

    public Customer(String name, String address, String phone, double creditLimit, double debitLimit) {
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.phone = new SimpleStringProperty(phone);
        this.credit = new SimpleDoubleProperty(creditLimit);
        this.debit = new SimpleDoubleProperty(debitLimit);
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

    public double getCredit() {
        return credit.get();
    }

    public void setCredit(double credit) {
        this.credit.set(credit);
    }

    public DoubleProperty creditProperty() {
        return credit;
    }

    public double getDebit() {
        return debit.get();
    }

    public void setDebit(double debit) {
        this.debit.set(debit);
    }

    public DoubleProperty debitProperty() {
        return debit;
    }

}
