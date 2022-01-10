package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.time.LocalDateTime;

public class Order {

    private final IntegerProperty customerId;
    private final LocalDateTime dateTime;

    public Order(int customerId, LocalDateTime dateTime) {
        this.customerId = new SimpleIntegerProperty(customerId);
        this.dateTime = dateTime;
    }

}
