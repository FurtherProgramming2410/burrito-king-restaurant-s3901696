package controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Model;

public class DashboardController {
    private Model model;
    private Stage stage;

    public DashboardController(Stage stage, Model model) {
        this.stage = new Stage();
        this.model = model;
    }

    @FXML
    public void initialize() {
        // Initialize the dashboard view if necessary
    }

    public void showStage(Pane root) {
        Scene scene = new Scene(root, 500, 300);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Dashboard");
        stage.show();
    }
}
