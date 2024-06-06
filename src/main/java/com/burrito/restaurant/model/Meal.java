package com.burrito.restaurant.model;

public class Meal extends FoodItem {
    public Meal(double unitPrice, int quantity) {
        super(unitPrice, quantity);
    }
    public void addQuantity(int additionalQuantity) {
        this.quantity += additionalQuantity;
    }

}