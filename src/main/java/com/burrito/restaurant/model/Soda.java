package com.burrito.restaurant.model;

public class Soda extends FoodItem {
    public Soda(double unitPrice, int quantity) {
        super(unitPrice, quantity);
    }

    public void addQuantity(int additionalQuantity) {
        this.quantity += additionalQuantity;
    }
}
