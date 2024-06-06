package com.burrito.restaurant.db;

import com.burrito.restaurant.model.FoodItem;

import java.sql.SQLException;

/**
 * Data access object interface for FoodItem.
 */
public interface FoodItemDao {

    FoodItem getFoodItem(int itemId) throws SQLException;

    void addFoodItem(FoodItem foodItem) throws SQLException;

    void updateFoodItem(int itemId, FoodItem updatedFoodItem) throws SQLException;

    void deleteFoodItem(int itemId) throws SQLException;
}
