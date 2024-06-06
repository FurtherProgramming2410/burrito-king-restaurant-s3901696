package com.burrito.restaurant.util;

import com.burrito.restaurant.BurritoApp;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Utility class for loading FXML views and transferring data between controllers.
 */
public class FXUtil {

    public static final String LOGIN_VIEW = "login-view.fxml";
    public static final String SIGN_UP_VIEW = "signup-view.fxml";
    public static final String DASH_VIEW = "dash-view.fxml";
    public static final String EDIT_PROFILE_VIEW = "edit-profile-view.fxml";

    /**
     * Load an FXML view in a new stage and transfer data to its controller.
     *
     * @param event   The event that triggers the view loading.
     * @param fxSource The FXML file to load.
     * @param title    The title of the stage.
     * @param data     The data to transfer to the controller.
     */
    public static void loadView(Event event, String fxSource, String title, Object... data) {
        try {
            // Hide the current window
            ((Node) event.getSource()).getScene().getWindow().hide();

            // Load FXML
            FXMLLoader loader = new FXMLLoader(BurritoApp.class.getResource(fxSource));
            Parent layout = loader.load();

            // Transfer data to the controller
            if (data.length > 0) {
                DataTraveler controller = loader.getController();
                controller.data(data);
            }

            // Show the new stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(layout);
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
