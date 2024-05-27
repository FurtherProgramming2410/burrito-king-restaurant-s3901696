package controller;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Model;

public class HomeController {
    private Model model;
    private Stage stage;
    private Stage parentStage;

    @FXML
    private Label welcomeLabel;
    @FXML
    private Button editProfileButton;
    @FXML
    private Button logoutButton;

    public HomeController(Stage parentStage, Model model) {
        this.stage = new Stage();
        this.parentStage = parentStage;
        this.model = model;
    }

    @FXML
    public void initialize() {
        if (model.getCurrentUser() != null) {
            welcomeLabel.setText("Welcome, " + model.getCurrentUser().getFirstName() + " " + model.getCurrentUser().getLastName());
        }

        editProfileButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditProfileView.fxml"));
                EditProfileController editProfileController = new EditProfileController(stage, model);
                loader.setController(editProfileController);
                Pane root = loader.load();
                editProfileController.showStage(root);
                stage.close();
            } catch (IOException e) {
                // Handle exception
            }
        });

        logoutButton.setOnAction(event -> {
            model.setCurrentUser(null);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));
                LoginController loginController = new LoginController(stage, model);
                loader.setController(loginController);
                Pane root = loader.load();
                loginController.showStage(root);
                stage.close();
            } catch (IOException e) {
                // Handle exception
            }
        });
    }

    public void showStage(Pane root) {
        Scene scene = new Scene(root, 500, 300);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Home");
        stage.show();
    }
}