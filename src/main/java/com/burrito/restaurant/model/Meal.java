package com.burrito.restaurant.model;

public class Meal extends FoodItem {
    public Meal(double unitPrice, int quantity) {
        super(unitPrice);
        this.setItemName(this.getClass().getSimpleName());
    }
}
