package com.burrito.restaurant.dao;

import com.burrito.restaurant.model.FoodItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FoodItemDaoImpl implements FoodItemDao {
    private final String TABLE_NAME = "food_items";

    public FoodItemDaoImpl() {
    }

    @Override
    public void setup() throws SQLException {
        try (Connection connection = Database.getConnection();
             Statement stmt = connection.createStatement();) {
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    "item_id SERIAL PRIMARY KEY," +
                    "unit_price NUMERIC NOT NULL," +
                    "quantity INT NOT NULL)";
            stmt.executeUpdate(sql);
        }
    }

    @Override
    public FoodItem getFoodItem(int itemId) throws SQLException {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE item_id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setInt(1, itemId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    double unitPrice = rs.getDouble("unit_price");
                    int quantity = rs.getInt("quantity");
                    return new FoodItem(unitPrice, quantity);
                }
                return null;
            }
        }
    }

    @Override
    public void addFoodItem(FoodItem foodItem) throws SQLException {
        String sql = "INSERT INTO " + TABLE_NAME + " (unit_price, quantity) VALUES (?, ?)";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setDouble(1, foodItem.getUnitPrice());
            stmt.setInt(2, foodItem.getQuantity());

            stmt.executeUpdate();
        }
    }

    @Override
    public void updateFoodItem(int itemId, FoodItem updatedFoodItem) throws SQLException {
        String sql = "UPDATE " + TABLE_NAME + " SET unit_price = ?, quantity = ? WHERE item_id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, updatedFoodItem.getUnitPrice());
            stmt.setInt(2, updatedFoodItem.getQuantity());
            stmt.setInt(3, itemId);

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteFoodItem(int itemId) throws SQLException {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE item_id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, itemId);

            stmt.executeUpdate();
        }
    }
}
