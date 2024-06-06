package com.burrito.restaurant.old;

import java.util.HashMap;
import java.util.LinkedList;

/*
 * The class that maintains a list of food items ordered by a user
 */
public class Order {
    private LinkedList<FoodItem> items;

    public Order() {
        items = new LinkedList<FoodItem>();
    }

    public void addFoodItem(FoodItem newItem) {
        for (FoodItem item : items) {
            if (item.getClass().getName().equals(newItem.getClass().getName())) {
                item.addQuantity(newItem.getQuantity());
                return;
            }
        }
        items.add(newItem);
    }

    public LinkedList<FoodItem> getItems() {
        return this.items;
    }

    /*
     * The method for calculating the total price of an order
     */
    public double getTotalPrice() {
        double sum = 0.0;
        for (FoodItem item : items) {
            sum += item.getTotalPrice();
        }
        return sum;
    }

    /*
     * The method for calculating the total preparation time of an order
     */
    public double getPrepTime(Restaurant restaurant) {
        HashMap<String, Integer> cookables = this.mapToCookables();
        double cookTimeForBurritos = new Burrito(Restaurant.getPrice("Burrito"), cookables.get("Burritos")).getPreparationTime(restaurant);
        double cookTimeForFries = new Fries(Restaurant.getPrice("Fries"), cookables.get("Fries")).getPreparationTime(restaurant);
        return Math.max(cookTimeForFries, cookTimeForBurritos);

    }

    /*
     * The method that maps a list of ordered food items to a map
     * containing the number of different items to be cooked.
     * Burritos and Fries are items that need to be cooked. Soda is not cookable.
     * For example, an order of one meal will be mapped to {Burrito=1, Fries=1}
     * An order of two burritos, one soda, and one meal will be mapped to {Burrito=3, Fries=1}
     */
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
        HashMap<String, Integer> mapped = new HashMap<String, Integer>();
        mapped.put("Burritos", numOfBurritos);
        mapped.put("Fries", numOfFries);
        return mapped;
    }

}

