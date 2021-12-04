module StoreApp {
    requires controlsfx;
    requires fontawesomefx;
    requires com.jfoenix;
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