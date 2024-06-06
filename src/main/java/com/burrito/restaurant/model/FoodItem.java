package com.burrito.restaurant.model;

public class FoodItem {
    private double unitPrice;
    private int quantity;

    public FoodItem(double unitPrice, int quantity) {
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addQuantity(int count) {
        this.quantity += count;
    }

    public double getTotalPrice() {
        return unitPrice * quantity;
    }
}
