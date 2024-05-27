package controller;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Model;
import model.User;

public class SignupController {
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private Button createUser;
    @FXML
    private Button close;
    @FXML
    private Label status;

    private Stage stage;
    private Stage parentStage;
    private Model model;

    public SignupController(Stage parentStage, Model model) {
        this.stage = new Stage();
        this.parentStage = parentStage;
        this.model = model;
    }

    @FXML
    public void initialize() {
        createUser.setOnAction(event -> {
            if (!username.getText().isEmpty() && !password.getText().isEmpty() && !firstName.getText().isEmpty() && !lastName.getText().isEmpty()) {
                User user;
                try {
                    user = model.getUserDao().createUser(username.getText(), password.getText(), firstName.getText(), lastName.getText());
                    if (user != null) {
                        status.setText("User created successfully!");
                        model.setCurrentUser(user);
                        // Navigate back to Login View
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));
                            LoginController loginController = new LoginController(parentStage, model);
                            loader.setController(loginController);
                            Pane root = loader.load();
                            loginController.showStage(root);
                            stage.close();
                        } catch (IOException e) {
                            status.setText("Navigation failed: " + e.getMessage());
                        }
                    } else {
                        status.setText("Cannot create user");
                    }
                } catch (SQLException e) {
                    status.setText("Database error: " + e.getMessage());
                }
            } else {
                status.setText("All fields are required");
            }
        });

        close.setOnAction(event -> {
            stage.close();
            parentStage.show();
        });
    }

    public void showStage(Pane root) {
        Scene scene = new Scene(root, 500, 300);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Sign up");
        stage.show();
    }
}
