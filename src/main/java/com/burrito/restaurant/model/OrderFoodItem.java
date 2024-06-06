package com.burrito.restaurant.model;

import java.util.Objects;

public class OrderFoodItem {

    private int id;
    private int orderId;
    private int foodItemId;
    private int quantity;

    public OrderFoodItem() {
    }

    public OrderFoodItem(int orderId, int foodItemId, int quantity) {
        this.orderId = orderId;
        this.foodItemId = foodItemId;
        this.quantity = quantity;
    }

    public OrderFoodItem(int id, int orderId, int foodItemId, int quantity) {
        this.id = id;
        this.orderId = orderId;
        this.foodItemId = foodItemId;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getFoodItemId() {
        return foodItemId;
    }

    public void setFoodItemId(int foodItemId) {
        this.foodItemId = foodItemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderFoodItem that = (OrderFoodItem) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "OrderFoodItem{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", foodItemId=" + foodItemId +
                '}';
    }
}
