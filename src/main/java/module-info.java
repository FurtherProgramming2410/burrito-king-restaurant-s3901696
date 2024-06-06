module com.burrito.restaurant {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.burrito.restaurant to javafx.fxml;
    exports com.burrito.restaurant;
}