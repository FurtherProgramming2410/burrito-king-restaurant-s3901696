package com.burrito.restaurant.model;

public interface Cookable {
    /**
     * The method to calculate the total preparation time of a food item
     */
    int getPreparationTime(Restaurant restaurant);

    /**
     * The method to calculate the actual number of items that need to be cooked
     */
    int getActualQuantityCooked(Restaurant restaurant);
}
