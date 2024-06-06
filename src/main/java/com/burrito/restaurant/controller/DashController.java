package com.burrito.restaurant.controller;


import com.burrito.restaurant.db.UserDetails;
import com.burrito.restaurant.model.Order;
import com.burrito.restaurant.model.User;
import com.burrito.restaurant.util.FXUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class DashController implements Initializable {

    @FXML
    private Label titleTxtLbl;
    @FXML
    private TableView<Order> orderTableView;

    private User user = UserDetails.getCurrentUser();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleTxtLbl.setText("Hi, " + user.getFirstName() + " " + user.getLastName());
    }

    public void logoutBtnAction(ActionEvent actionEvent) {
        UserDetails.setCurrentUser(null);
        FXUtil.loadView(actionEvent, FXUtil.LOGIN_VIEW, "Login");
    }

    public void editProfileBtnAction(ActionEvent actionEvent) {

    }

    public void placeOrderBtnAction(ActionEvent actionEvent) {

    }

}
