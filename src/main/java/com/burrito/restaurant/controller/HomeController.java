package com.burrito.restaurant.controller;

import com.burrito.restaurant.BurritoApp;
import com.burrito.restaurant.model.UserDetails;
import com.burrito.restaurant.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.IOException;


public class HomeController {

	private UserDetails userDetails;
	private Stage stage;
	private Stage parentStage;

	@FXML
	private Button editUserBtn;

	@FXML
	private Label userName;

	@FXML
	public void initialize() {
		updateUserInfo();
		editUserBtn.setOnAction(event -> {
			try {
				FXMLLoader loader = new FXMLLoader(BurritoApp.class.getResource("EditProfileView.fxml"));
				// Customize controller instance
				EditProfileView editProfileView =  new EditProfileView(userDetails, stage);
				loader.setController(editProfileView);
				VBox root = loader.load();
				editProfileView.showStage(root);
				stage.close();
			} catch (IOException e) {
				e.getMessage();
			}});
	}

	public void setModel(UserDetails userDetails) {
		this.userDetails = userDetails;
		// After setting the model, update the user info to reflect the current user.
		updateUserInfo();
	}

	private void updateUserInfo() {
		if (userDetails != null) {
			User user = userDetails.getCurrentUser();
			if (user != null) {
				String firstName = user.getFirstName();
				String lastName = user.getLastName();
				userName.setText(firstName + " " + lastName);
			} else {
				userName.setText("No user data available");
			}
		}
	}

	public HomeController(Stage parentStage, UserDetails userDetails) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.userDetails = userDetails;
	}

	// Add your code to complete the functionality of the program
	public void showStage(Pane root) {
		Scene scene = new Scene(root, 700, 500);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Home");
		stage.show();
	}
}
