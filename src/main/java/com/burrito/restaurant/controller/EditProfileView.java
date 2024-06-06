package com.burrito.restaurant.controller;

import com.burrito.restaurant.model.Model;
import com.burrito.restaurant.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class EditProfileView {

    private Model model;
    private Stage stage;
    private Stage parentStage;


    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private PasswordField password;

    @FXML
    private Label status;

    @FXML
    private Button updateUser;

    @FXML
    private TextField username;

    public EditProfileView(Model model, Stage parentStage) {
        this.stage = new Stage();
        this.model = model;
        this.parentStage = parentStage;
    }

    @FXML
    public void initialize() {

        updateUser.setOnAction(event -> {
            if (model != null && model.getUserDao() != null) {
                User user = model.getCurrentUser();
                if (user != null) {
                    try {
                        User updatedUser = model.getUserDao().updateUser(user.getUsername(), password.getText(), firstName.getText(), lastName.getText());
                        if (updatedUser != null) {
                            status.setText("Updated " + updatedUser.getUsername());
                            status.setTextFill(Color.GREEN);
                            // Close the current stage
                            stage.close();
                            // Load the login view
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/burrito/restaurant/LoginView.fxml"));
                            LoginController loginController = new LoginController(parentStage, model); // Assuming parentStage is the login view's stage
                            loader.setController(loginController);
                            Parent root = loader.load();
                            Scene scene = new Scene(root);
                            parentStage.setScene(scene);
                            parentStage.show();

                        } else {
                            status.setText("Cannot Update user");
                            status.setTextFill(Color.RED);
                        }
                    } catch (SQLException e) {
                        status.setText("Error updating user: " + e.getMessage());
                        status.setTextFill(Color.RED);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    username.setText("No user data available");
                    firstName.setText("No user data available");
                    lastName.setText("No user data available");
                    password.setText("No user data available");
                }
            } else {
                status.setText("Model or UserDao not available");
                status.setTextFill(Color.RED);
            }
        });

        setUserInfo();
    }

    public void setModel(Model model) {
        this.model = model;
        setUserInfo();
    }

    // Add your code to complete the functionality of the program
    public void showStage(Pane root) {
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Home");
        stage.show();
    }

    private void setUserInfo() {
        if (model != null) {
            User user = model.getCurrentUser();
            if (user != null) {
                String firstname = user.getFirstName();
                String lastname = user.getLastName();
                String password1 = user.getPassword();
                String userName = user.getUsername();
                username.setText(userName);
                firstName.setText(firstname);
                lastName.setText(lastname);
                password.setText(password1);
            } else {
                username.setText("No user data available");
                firstName.setText("No user data available");
                lastName.setText("No user data available");
                password.setText("No user data available");
            }
        }
    }

}
