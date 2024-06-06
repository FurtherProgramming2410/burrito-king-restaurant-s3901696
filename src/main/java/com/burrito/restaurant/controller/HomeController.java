package com.burrito.restaurant.controller;

import com.burrito.restaurant.model.Model;
import com.burrito.restaurant.model.User;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.sql.SQLException;


public class HomeController {

	private Model model;
	private Stage stage;
	private Stage parentStage;

	@FXML
	private Button editUserBtn;

	@FXML
	private Label userName;

	@FXML
	public void initialize() {

	}


	public HomeController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}


	@FXML
	void onActoinEditUser(ActionEvent event) {
	}

	// Add your code to complete the functionality of the program
	public void showStage(Pane root) {
		Scene scene = new Scene(root, 500, 300);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Home");
		stage.show();
	}
}
