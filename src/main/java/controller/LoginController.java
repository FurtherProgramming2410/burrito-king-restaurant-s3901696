package controller;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Model;
import model.User;

public class LoginController {
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

    private Model model;
    private Stage stage;

    public LoginController(Stage stage, Model model) {
        this.stage = stage;
        this.model = model;
    }

    @FXML
    public void initialize() {
        login.setOnAction(event -> {
            try {
                handleLogin();
            } catch (SQLException e) {
                message.setText("Database error: " + e.getMessage());
                message.setStyle("-fx-text-fill: red;");
            }
        });

        signup.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SignupView.fxml"));
                SignupController signupController = new SignupController(stage, model);
                loader.setController(signupController);
                Pane root = loader.load();
                signupController.showStage(root);
                stage.close();
            } catch (IOException e) {
                message.setText("Failed to load signup screen: " + e.getMessage());
                message.setStyle("-fx-text-fill: red;");
            }
        });
    }

    private void handleLogin() throws SQLException {
        if (!name.getText().isEmpty() && !password.getText().isEmpty()) {
            User user = model.getUserDao().getUser(name.getText(), password.getText());
            if (user != null) {
                model.setCurrentUser(user);
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DashboardView.fxml"));
                    DashboardController dashboardController = new DashboardController(stage, model);
                    loader.setController(dashboardController);
                    Pane root = loader.load();
                    dashboardController.showStage(root);
                    stage.close();
                } catch (IOException e) {
                    message.setText("Failed to load dashboard: " + e.getMessage());
                    message.setStyle("-fx-text-fill: red;");
                }
            } else {
                message.setText("Wrong username or password");
                message.setStyle("-fx-text-fill: red;");
            }
        } else {
            message.setText("Empty username or password");
            message.setStyle("-fx-text-fill: red;");
        }
        name.clear();
        password.clear();
    }

    public void showStage(Pane root) {
        Scene scene = new Scene(root, 500, 300);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Welcome");
        stage.show();
    }
}
