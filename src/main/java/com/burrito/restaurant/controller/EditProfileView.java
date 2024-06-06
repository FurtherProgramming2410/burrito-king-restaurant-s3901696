package com.burrito.restaurant.controller;

import com.burrito.restaurant.model.Model;
import com.burrito.restaurant.model.User;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EditProfileView {

    private Model model;
    private Stage stage;
    private Stage parentStage;

    @FXML
    private Button back;

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

        setUserInfo();
        back.setOnAction(event -> {
            stage.close();
            parentStage.show();
        });
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
