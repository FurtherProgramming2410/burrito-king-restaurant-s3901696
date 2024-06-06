module com.burrito.restaurant {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.slf4j;

    opens com.burrito.restaurant to javafx.fxml;
    exports com.burrito.restaurant;

    opens com.burrito.restaurant.controller;
    opens com.burrito.restaurant.model;
    opens com.burrito.restaurant.dao;
    exports com.burrito.restaurant.controller;
    exports com.burrito.restaurant.model;
    exports com.burrito.restaurant.dao;
    exports com.burrito.restaurant.old;
    exports com.burrito.restaurant.implementation;
    opens com.burrito.restaurant.implementation;


}