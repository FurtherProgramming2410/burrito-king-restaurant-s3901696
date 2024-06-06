package com.burrito.restaurant.controller;

import com.burrito.restaurant.db.UserDetails;
import com.burrito.restaurant.model.*;
import com.burrito.restaurant.util.FXUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class PlaceOrderController implements Initializable {

    @FXML
    public Label burritoTxtLbl;
    @FXML
    public Button burritoRemoveBtn;
    @FXML
    public Button burritoAddBtn;
    @FXML
    public Label friesTxtLbl;
    @FXML
    public Button friesRemoveBtn;
    @FXML
    public Button friesAddBtn;
    @FXML
    public Label sodaTxtLbl;
    @FXML
    public Button sodaRemoveBtn;
    @FXML
    public Button sodaAddBtn;
    @FXML
    public Label mealTxtLbl;
    @FXML
    public Button mealRemoveBtn;
    @FXML
    public Button mealAddBtn;
    @FXML
    public TextArea invoiceTxtArea;
    @FXML
    private Label titleTxtLbl;

    private User user = UserDetails.getCurrentUser();
    private Order order = new Order();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleTxtLbl.setText("Hi, " + user.getFirstName() + " " + user.getLastName());
        order.setUserId(user.getId());
        updateInvoice();
    }

    public void logoutBtnAction(ActionEvent actionEvent) {
        UserDetails.setCurrentUser(null);
        FXUtil.loadView(actionEvent, FXUtil.LOGIN_VIEW, "Login");
    }

    public void backBtnAction(ActionEvent actionEvent) {
        FXUtil.loadView(actionEvent, FXUtil.DASH_VIEW, "Dashboard");
    }

    public void submitOrderBtnAction(ActionEvent actionEvent) {
        // Here you would save the order to the database and possibly navigate to a confirmation view
        // For now, we'll just display a confirmation message in the invoice area
        invoiceTxtArea.setText("Order submitted!\n" + invoiceTxtArea.getText());
        // Optionally, clear the order
        order = new Order();
        updateInvoice();
    }

    public void burritoAddAction(ActionEvent actionEvent) {
        updateInvoice();
    }

    public void burritoRemoveAction(ActionEvent actionEvent) {
        updateInvoice();
    }

    public void friesAddAction(ActionEvent actionEvent) {
        updateInvoice();
    }

    public void friesRemoveAction(ActionEvent actionEvent) {
        updateInvoice();
    }

    public void sodaAddAction(ActionEvent actionEvent) {
        updateInvoice();
    }

    public void sodaRemoveAction(ActionEvent actionEvent) {
        order.removeFoodItem(Soda.class, 1);
        updateInvoice();
    }

    public void mealAddAction(ActionEvent actionEvent) {
        updateInvoice();
    }

    public void mealRemoveAction(ActionEvent actionEvent) {
        order.removeFoodItem(Meal.class, 1);
        updateInvoice();
    }

    private void updateInvoice() {
        StringBuilder invoice = new StringBuilder();
        invoice.append("Order for ").append(user.getFirstName()).append(" ").append(user.getLastName()).append("\n\n");

        invoice.append("\nTotal Price: $").append("12");
        invoiceTxtArea.setText(invoice.toString());
    }


}