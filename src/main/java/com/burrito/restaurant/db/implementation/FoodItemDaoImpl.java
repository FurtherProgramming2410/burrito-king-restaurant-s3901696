package com.burrito.restaurant.db.implementation;

import com.burrito.restaurant.db.DbConnection;
import com.burrito.restaurant.db.FoodItemDao;
import com.burrito.restaurant.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FoodItemDaoImpl implements FoodItemDao {

    private static final Logger logger = LoggerFactory.getLogger(FoodItemDaoImpl.class);
    private final String TABLE_NAME = "food_items";

    public FoodItemDaoImpl() {
        try (Connection connection = DbConnection.getConnection();
             Statement stmt = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    "item_id SERIAL PRIMARY KEY," +
                    "unit_price NUMERIC NOT NULL," +
                    "quantity INT NOT NULL," +
                    "type VARCHAR(50) NOT NULL)";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            logger.error("Error while setup " + this.getClass().getSimpleName());
        }
    }

    @Override
    public FoodItem getFoodItem(int itemId) throws SQLException {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE item_id = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, itemId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    double unitPrice = rs.getDouble("unit_price");
                    int quantity = rs.getInt("quantity");
                    String type = rs.getString("type");

                    switch (type) {
                        case "Meal":
                            return new Meal(unitPrice, quantity);
                        case "Soda":
                            return new Soda(unitPrice, quantity);
                        case "Burrito":
                            return new Burrito(unitPrice, quantity);
                        case "Fries":
                            return new Fries(unitPrice, quantity);
                        default:
                            throw new IllegalArgumentException("Unknown food item type: " + type);
                    }
                }
                return null;
            }
        }
    }

    @Override
    public void addFoodItem(FoodItem foodItem) throws SQLException {
        String sql = "INSERT INTO " + TABLE_NAME + " (unit_price, quantity, type) VALUES (?, ?, ?)";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, foodItem.getUnitPrice());
            stmt.setInt(2, foodItem.getQuantity());
            stmt.setString(3, foodItem.getClass().getSimpleName());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    foodItem.setItemId(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public void updateFoodItem(int itemId, FoodItem updatedFoodItem) throws SQLException {
        String sql = "UPDATE " + TABLE_NAME + " SET unit_price = ?, quantity = ?, type = ? WHERE item_id = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, updatedFoodItem.getUnitPrice());
            stmt.setInt(2, updatedFoodItem.getQuantity());
            stmt.setString(3, updatedFoodItem.getClass().getSimpleName());
            stmt.setInt(4, itemId);

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteFoodItem(int itemId) throws SQLException {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE item_id = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, itemId);

            stmt.executeUpdate();
        }
    }
}
