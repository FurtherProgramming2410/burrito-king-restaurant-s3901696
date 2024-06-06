package com.burrito.restaurant.controller;

import com.burrito.restaurant.db.UserDao;
import com.burrito.restaurant.db.implementation.UserDaoImpl;
import com.burrito.restaurant.model.User;
import com.burrito.restaurant.util.FXUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    public TextField userNameTxtFld;
    @FXML
    public PasswordField passwordTxtFld;
    @FXML
    public Label errMsgTxtLbl;

    private UserDao userDao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.userDao = new UserDaoImpl();
    }

    // Login Button
    public void loginBtnAction(ActionEvent actionEvent) {
        String username = userNameTxtFld.getText();
        String password = passwordTxtFld.getText();

        if (validateInputs(username, password)) {
            try {
                User user = userDao.login(username, password);
                if (user != null) {
                    errMsgTxtLbl.setText("Login successful.");
                    // Navigate to the next view (e.g., dashboard)
                    FXUtil.loadView(actionEvent, FXUtil.DASH_VIEW, "Dashboard");
                } else {
                    errMsgTxtLbl.setText("Invalid username or password.");
                }
            } catch (SQLException e) {
                errMsgTxtLbl.setText("An error occurred during login.");
                e.printStackTrace();
            }
        }
    }

    // Signup Button
    public void signupBtnAction(ActionEvent actionEvent) {
        FXUtil.loadView(actionEvent, FXUtil.SIGN_UP_VIEW, "Signup");
    }

    // Validate Inputs
    private boolean validateInputs(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            errMsgTxtLbl.setText("Username is required.");
            return false;
        }
        if (password == null || password.trim().isEmpty()) {
            errMsgTxtLbl.setText("Password is required.");
            return false;
        }
        return true;
    }
}