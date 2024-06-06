package com.burrito.restaurant.model;

public class Soda extends FoodItem {
    public Soda(double unitPrice, int quantity) {
        super(unitPrice);
        this.setItemName(this.getClass().getSimpleName());
    }
}
