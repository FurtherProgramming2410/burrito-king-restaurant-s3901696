package com.burrito.restaurant.model;

import java.util.Objects;

public class FoodItem {

    private int itemId; // Add this field
    private String itemName;
    private double unitPrice;

    public FoodItem() {
    }

    public FoodItem(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public FoodItem(String itemName, double unitPrice) {
        this.itemName = itemName;
        this.unitPrice = unitPrice;
    }

    public FoodItem(int itemId, String itemName, double unitPrice) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodItem foodItem = (FoodItem) o;
        return itemId == foodItem.itemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId);
    }
}
