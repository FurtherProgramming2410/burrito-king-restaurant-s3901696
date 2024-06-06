package com.burrito.restaurant;

import com.burrito.restaurant.controller.LoginController;
import com.burrito.restaurant.model.UserDetails;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class BurritoApp extends Application {

    private UserDetails userDetails;

    @Override
    public void init() {
        userDetails = new UserDetails();
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(BurritoApp.class.getResource("LoginView.fxml"));

            // Customize controller instance
            LoginController loginController = new LoginController(primaryStage, userDetails);

            loader.setController(loginController);

            GridPane root = loader.load();

            loginController.showStage(root);

        } catch (IOException | RuntimeException e) {
            Scene scene = new Scene(new Label(e.getMessage()), 400, 200);
            primaryStage.setTitle("Error");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
