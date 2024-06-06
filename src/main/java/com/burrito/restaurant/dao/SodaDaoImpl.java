package com.burrito.restaurant.dao;

import com.burrito.restaurant.model.Soda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SodaDaoImpl implements SodaDao {
    private final String TABLE_NAME = "sodas";

    public SodaDaoImpl() {
    }

    @Override
    public void setup() throws SQLException {
        try (Connection connection = Database.getConnection();
             Statement stmt = connection.createStatement();) {
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    "soda_id SERIAL PRIMARY KEY," +
                    "unit_price NUMERIC NOT NULL," +
                    "quantity INT NOT NULL)";
            stmt.executeUpdate(sql);
        }
    }

    @Override
    public Soda getSoda(int sodaId) throws SQLException {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE soda_id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setInt(1, sodaId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    double unitPrice = rs.getDouble("unit_price");
                    int quantity = rs.getInt("quantity");
                    return new Soda(unitPrice, quantity);
                }
                return null;
            }
        }
    }

    @Override
    public void addSoda(Soda soda) throws SQLException {
        String sql = "INSERT INTO " + TABLE_NAME + " (unit_price, quantity) VALUES (?, ?)";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setDouble(1, soda.getUnitPrice());
            stmt.setInt(2, soda.getQuantity());

            stmt.executeUpdate();
        }
    }

    @Override
    public void updateSoda(int sodaId, Soda updatedSoda) throws SQLException {
        String sql = "UPDATE " + TABLE_NAME + " SET unit_price = ?, quantity = ? WHERE soda_id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, updatedSoda.getUnitPrice());
            stmt.setInt(2, updatedSoda.getQuantity());
            stmt.setInt(3, sodaId);

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteSoda(int sodaId) throws SQLException {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE soda_id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, sodaId);

            stmt.executeUpdate();
        }
    }
}
