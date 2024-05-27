package controller;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Model;

public class EditProfileController {
    private Model model;
    private Stage stage;

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField password;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label status;

    public EditProfileController(Stage stage, Model model) {
        this.stage = new Stage();
        this.model = model;
    }

    @FXML
    public void initialize() {
        if (model.getCurrentUser() != null) {
            firstName.setText(model.getCurrentUser().getFirstName());
            lastName.setText(model.getCurrentUser().getLastName());
        }

        saveButton.setOnAction(event -> {
            try {
                boolean updated = model.updateUserProfile(firstName.getText(), lastName.getText(), password.getText());
                if (updated) {
                    status.setText("Profile updated successfully!");
                } else {
                    status.setText("Failed to update profile.");
                }
            } catch (SQLException e) {
                status.setText("Database error: " + e.getMessage());
            }
        });

        cancelButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomeView.fxml"));
                HomeController homeController = new HomeController(stage, model);
                loader.setController(homeController);
                Pane root = loader.load();
                homeController.showStage(root);
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
        stage.setTitle("Edit Profile");
        stage.show();
    }
}