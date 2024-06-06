package com.burrito.restaurant.controller;

import com.burrito.restaurant.db.UserDao;
import com.burrito.restaurant.db.implementation.UserDaoImpl;
import com.burrito.restaurant.model.User;
import com.burrito.restaurant.util.FXUtil;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignupController implements Initializable {

    public TextField usernameTxtLbl;
    public PasswordField passwordTxtLbl;
    public TextField firstNameTxtLbl;
    public TextField lastNameTxtLbl;
    public Label errMsgTxtLbl;

    private UserDao userDao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userDao = new UserDaoImpl();
    }

    // Signup Button
    public void signupBtnAction(ActionEvent actionEvent) {
        String username = usernameTxtLbl.getText();
        String password = passwordTxtLbl.getText();
        String firstName = firstNameTxtLbl.getText();
        String lastName = lastNameTxtLbl.getText();

        if (username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
            errMsgTxtLbl.setText("All fields are required.");
            return;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setVip(false);
        user.setEmail(null);
        user.setPoints(0);

        try {
            User existingUser = userDao.getByUsername(username);
            if (existingUser != null) {
                errMsgTxtLbl.setText("Username already exists.");
                return;
            }

            userDao.create(user);
            errMsgTxtLbl.setText("User created successfully.");
            FXUtil.loadView(actionEvent, FXUtil.LOGIN_VIEW, "Login");
        } catch (SQLException e) {
            errMsgTxtLbl.setText("An error occurred while creating the user.");
            e.printStackTrace();
        }
    }

    // Back Button
    public void backBtnAction(ActionEvent actionEvent) {
        FXUtil.loadView(actionEvent, FXUtil.LOGIN_VIEW, "Login");
    }
}