package com.burrito.restaurant.dao;

import com.burrito.restaurant.model.Meal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MealDaoImpl implements MealDao {
    private final String TABLE_NAME = "meals";

    public MealDaoImpl() {
    }

    @Override
    public void setup() throws SQLException {
        try (Connection connection = Database.getConnection();
             Statement stmt = connection.createStatement();) {
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    "meal_id SERIAL PRIMARY KEY," +
                    "unit_price NUMERIC NOT NULL," +
                    "quantity INT NOT NULL)";
            stmt.executeUpdate(sql);
        }
    }

    @Override
    public Meal getMeal(int mealId) throws SQLException {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE meal_id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setInt(1, mealId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    double unitPrice = rs.getDouble("unit_price");
                    int quantity = rs.getInt("quantity");
                    return new Meal(unitPrice, quantity);
                }
                return null;
            }
        }
    }

    @Override
    public void addMeal(Meal meal) throws SQLException {
        String sql = "INSERT INTO " + TABLE_NAME + " (unit_price, quantity) VALUES (?, ?)";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setDouble(1, meal.getUnitPrice());
            stmt.setInt(2, meal.getQuantity());

            stmt.executeUpdate();
        }
    }

    @Override
    public void updateMeal(int mealId, Meal updatedMeal) throws SQLException {
        String sql = "UPDATE " + TABLE_NAME + " SET unit_price = ?, quantity = ? WHERE meal_id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, updatedMeal.getUnitPrice());
            stmt.setInt(2, updatedMeal.getQuantity());
            stmt.setInt(3, mealId);

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteMeal(int mealId) throws SQLException {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE meal_id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, mealId);

            stmt.executeUpdate();
        }
    }
}
