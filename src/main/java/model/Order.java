package model;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.HashMap;

public class Order {
    private int orderId;
    private String username;
    private LinkedList<FoodItem> items;
    private LocalDateTime orderTime;
    private double totalPrice;
    private String status;

    public Order() {
        items = new LinkedList<>();
        orderTime = LocalDateTime.now();
        status = "placed";
    }

    // Getters and Setters
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public LinkedList<FoodItem> getItems() { return items; }
    public void setItems(LinkedList<FoodItem> items) { this.items = items; }

    public LocalDateTime getOrderTime() { return orderTime; }
    public void setOrderTime(LocalDateTime orderTime) { this.orderTime = orderTime; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // Methods
    public void addFoodItem(FoodItem newItem) {
        for (FoodItem item : items) {
            if (item.getClass().getName().equals(newItem.getClass().getName())) {
                item.addQuantity(newItem.getQuantity());
                return;
            }
        }
        items.add(newItem);
    }

    public double calculateTotalPrice() {
        double sum = 0.0;
        for (FoodItem item : items) {
            sum += item.getTotalPrice();
        }
        return sum;
    }

    public HashMap<String, Integer> mapToCookables() {
        int numOfBurritos = 0;
        int numOfFries = 0;
        for (FoodItem item : items) {
            if (item instanceof Burrito) {
                numOfBurritos += item.getQuantity();
            } else if (item instanceof Fries) {
                numOfFries += item.getQuantity();
            } else if (item instanceof Meal) {
                numOfBurritos += item.getQuantity();
                numOfFries += item.getQuantity();
            }
        }
        HashMap<String, Integer> mapped = new HashMap<>();
        mapped.put("Burritos", numOfBurritos);
        mapped.put("Fries", numOfFries);
        return mapped;
    }
}