package com.burrito.restaurant;

import com.burrito.restaurant.controller.LoginController;
import com.burrito.restaurant.model.Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


public class BurritoApp extends Application {
    private Model model;

    @Override
    public void init() {
        model = new Model();
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            model.setup();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("LoginView.fxml"));
            // Customize controller instance
            LoginController loginController = new LoginController(primaryStage, model);

            loader.setController(loginController);

            GridPane root = loader.load();

            loginController.showStage(root);

        } catch (IOException | SQLException | RuntimeException e) {
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
