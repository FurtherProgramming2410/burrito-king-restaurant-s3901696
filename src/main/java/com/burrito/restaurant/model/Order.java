package com.burrito.restaurant.model;

import java.util.HashMap;
import java.util.LinkedList;

public class Order {

    private int orderId;
    private int userId;
    private LinkedList<OrderFoodItem> items;

    public Order() {
        items = new LinkedList<>();
    }

    public Order(int orderId, int userId, LinkedList<OrderFoodItem> items) {
        this.orderId = orderId;
        this.userId = userId;
        this.items = items;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setItems(LinkedList<OrderFoodItem> items) {
        this.items = items;
    }

    public void addFoodItem(OrderFoodItem newItem) {
        for (OrderFoodItem item : items) {
            if (item.getFoodItemId() == newItem.getOrderId()) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        items.add(newItem);
    }

    public void removeFoodItem(Class<? extends FoodItem> foodItemClass, int quantity) {
        items.removeIf(item -> item.getClass().equals(foodItemClass) && item.getQuantity() <= quantity);

//        for (FoodItem item : items) {
//            if (item.getClass().equals(foodItemClass)) {
//                if (item.getQuantity() > quantity) {
//                    item.setQuantity(item.getQuantity() - quantity);
//                } else {
//                    items.remove(item);
//                }
//                break;
//            }
//        }
    }



}
