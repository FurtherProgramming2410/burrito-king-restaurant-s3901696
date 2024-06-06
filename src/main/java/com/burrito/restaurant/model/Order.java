package com.burrito.restaurant.model;

import com.burrito.restaurant.model.Fries;

import java.util.HashMap;
import java.util.LinkedList;

public class Order {
    private LinkedList<FoodItem> items;

    public Order() {
        items = new LinkedList<>();
    }

    public void addFoodItem(FoodItem newItem) {
        for (FoodItem item : items) {
            if (item.getClass().equals(newItem.getClass())) {
                item.addQuantity(newItem.getQuantity());
                return;
            }
        }
        items.add(newItem);
    }

    public LinkedList<FoodItem> getItems() {
        return new LinkedList<>(items);
    }

    public double getTotalPrice() {
        double sum = 0.0;
        for (FoodItem item : items) {
            sum += item.getTotalPrice();
        }
        return sum;
    }

    public double getPrepTime(Restaurant restaurant) {
        HashMap<String, Integer> cookables = mapToCookables();
        double burritoPrice = restaurant.getPrice("Burrito");
        double friesPrice = restaurant.getPrice("Fries");

        double cookTimeForBurritos = new Burrito(burritoPrice, cookables.getOrDefault("Burritos", 0)).getPreparationTime(restaurant);
        double cookTimeForFries = new Fries(friesPrice, cookables.getOrDefault("Fries", 0)).getPreparationTime(restaurant);

        return Math.max(cookTimeForFries, cookTimeForBurritos);
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
