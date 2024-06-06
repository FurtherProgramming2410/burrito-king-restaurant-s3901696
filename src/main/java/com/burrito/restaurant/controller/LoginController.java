package com.burrito.restaurant.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private PasswordField password;
    @FXML
    private Label message;
    @FXML
    private Button login;
    @FXML
    private Button signup;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login.setOnAction(event -> {
            if (!name.getText().isEmpty() && !password.getText().isEmpty()) {

            } else {
                message.setText("Empty username or password");
            }
        });

        // Signup Button Action
        signup.setOnAction(event -> {

        });
    }
}

