package com.burrito.restaurant.model;
import java.util.HashMap;
import java.util.LinkedList;

public class Restaurant {
    private String name;
    private static final HashMap<String, Double> priceMap = new HashMap<>();
    private int remainedFries;
    private static final LinkedList<Order> allOrders = new LinkedList<>();
    private static final int mealDiscount = 3;

    public Restaurant(String name) {
        priceMap.put("Burrito", 7.0);
        priceMap.put("Fries", 4.0);
        priceMap.put("Soda", 2.5);
        this.remainedFries = 0;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static int getDiscount() {
        return mealDiscount;
    }

    public static double getPrice(String foodName) {
        return priceMap.get(foodName);
    }

    public LinkedList<Order> getAllOrders() {
        return new LinkedList<>(allOrders);
    }

    public int getRemainedFries() {
        return this.remainedFries;
    }

    public static void updatePrice(String foodName, double newPrice) {
        priceMap.put(foodName, newPrice);
    }

    public boolean updateRemainingServes(Order order) {
        HashMap<String, Integer> cookables = order.mapToCookables();
        Fries friesToCook = new Fries(Restaurant.getPrice("Fries"), cookables.get("Fries"));
        int oldRemained = this.remainedFries;
        this.remainedFries = friesToCook.getActualQuantityCooked(this) + oldRemained - friesToCook.getQuantity();
        return this.remainedFries != oldRemained;
    }

    public void addOrderToHistory(Order order) {
        allOrders.add(order);
    }
}
