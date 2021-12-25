package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Company {

    private final StringProperty companyName;
    private final StringProperty companyContact;
    private final StringProperty companyAddress;

    public Company(String companyName, String companyContact, String companyAddress) {
        this.companyName = new SimpleStringProperty(companyName);
        this.companyContact = new SimpleStringProperty(companyContact);
        this.companyAddress = new SimpleStringProperty(companyAddress);
    }

    public String getCompanyName() {
        return companyName.get();
    }

    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }

    public StringProperty companyNameProperty() {
        return companyName;
    }

    public String getCompanyContact() {
        return companyContact.get();
    }

    public void setCompanyContact(String companyContact) {
        this.companyContact.set(companyContact);
    }

    public StringProperty companyContactProperty() {
        return companyContact;
    }

    public String getCompanyAddress() {
        return companyAddress.get();
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress.set(companyAddress);
    }

    public StringProperty companyAddressProperty() {
        return companyAddress;
    }

}
