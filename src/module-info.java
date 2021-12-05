module StoreApp {
    requires javafx.fxml;
    requires javafx.controls;
    requires controlsfx;
    requires fontawesomefx;
    requires com.jfoenix;
    requires AnimateFX;
    requires poi;
    requires java.sql;
    exports controller;
    exports query;
    exports database;
    exports model;
    exports helper;
    exports factory;
    exports customWidgets;
    opens main;
}