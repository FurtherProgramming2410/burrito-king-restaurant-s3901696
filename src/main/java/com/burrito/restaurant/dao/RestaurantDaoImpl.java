package com.burrito.restaurant.dao;

import com.burrito.restaurant.model.Restaurant;

import java.sql.*;
import java.util.HashMap;

public class RestaurantDaoImpl implements RestaurantDao {
    private final String TABLE_NAME = "restaurants";

    public RestaurantDaoImpl() {
    }

    @Override
    public void setup() throws SQLException {
        try (Connection connection = Database.getConnection();
             Statement stmt = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    "name VARCHAR(50) PRIMARY KEY," +
                    "remained_fries INT NOT NULL)";
            stmt.executeUpdate(sql);
        }
    }

    @Override
    public Restaurant getRestaurant(String name) throws SQLException {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE name = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Restaurant restaurant = new Restaurant(name);
                    restaurant.setRemainedFries(rs.getInt("remained_fries"));
                    return restaurant;
                }
                return null;
            }
        }
    }

    @Override
    public void addRestaurant(Restaurant restaurant) throws SQLException {
        String sql = "INSERT INTO " + TABLE_NAME + " (name, remained_fries) VALUES (?, ?)";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, restaurant.getName());
            stmt.setInt(2, restaurant.getRemainedFries());
            stmt.executeUpdate();
        }
    }

    @Override
    public void updateRestaurant(String name, Restaurant updatedRestaurant) throws SQLException {
        String sql = "UPDATE " + TABLE_NAME + " SET remained_fries = ? WHERE name = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, updatedRestaurant.getRemainedFries());
            stmt.setString(2, name);
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteRestaurant(String name) throws SQLException {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE name = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        }
    }
}
