package com.burrito.restaurant.dao;

import com.burrito.restaurant.model.Meal;

import java.sql.SQLException;

/**
 * Data access object interface for Meal.
 */
public interface MealDao {
    void setup() throws SQLException;

    Meal getMeal(int mealId) throws SQLException;

    void addMeal(Meal meal) throws SQLException;

    void updateMeal(int mealId, Meal updatedMeal) throws SQLException;

    void deleteMeal(int mealId) throws SQLException;
}
