package com.burrito.restaurant;

import com.burrito.restaurant.util.FXUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class BurritoApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BurritoApp.class.getResource(FXUtil.LOGIN_VIEW));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("AIS-R");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
