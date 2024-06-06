package com.burrito.restaurant.dao;

import com.burrito.restaurant.model.*;

import java.sql.*;
import java.util.LinkedList;

public class OrderDaoImpl implements OrderDao {
    private final String TABLE_NAME = "orders";
    private final String ITEM_TABLE_NAME = "order_items";

    public OrderDaoImpl() {
    }

    @Override
    public void setup() throws SQLException {
        try (Connection connection = Database.getConnection();
             Statement stmt = connection.createStatement()) {
            String createOrderTableSql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    "order_id SERIAL PRIMARY KEY)";
            stmt.executeUpdate(createOrderTableSql);

            String createOrderItemsTableSql = "CREATE TABLE IF NOT EXISTS " + ITEM_TABLE_NAME + " (" +
                    "order_id INT REFERENCES " + TABLE_NAME + "(order_id)," +
                    "item_id SERIAL PRIMARY KEY," +
                    "unit_price NUMERIC NOT NULL," +
                    "quantity INT NOT NULL," +
                    "type VARCHAR(50) NOT NULL)";
            stmt.executeUpdate(createOrderItemsTableSql);
        }
    }

    @Override
    public Order getOrder(int orderId) throws SQLException {
        String sql = "SELECT * FROM " + ITEM_TABLE_NAME + " WHERE order_id = ?";
        Order order = new Order();
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String type = rs.getString("type");
                    double unitPrice = rs.getDouble("unit_price");
                    int quantity = rs.getInt("quantity");
                    FoodItem item;
                    switch (type) {
                        case "Burrito":
                            item = new Burrito(unitPrice, quantity);
                            break;
                        case "Fries":
                            item = new Fries(unitPrice, quantity);
                            break;
                        case "Soda":
                            item = new Soda(unitPrice, quantity);
                            break;
                        case "Meal":
                            item = new Meal(unitPrice, quantity);
                            break;
                        default:
                            throw new IllegalArgumentException("Unknown food item type: " + type);
                    }
                    item.setItemId(rs.getInt("item_id"));
                    order.addFoodItem(item);
                }
            }
        }
        return order;
    }

    @Override
    public void addOrder(Order order) throws SQLException {
        String insertOrderSql = "INSERT INTO " + TABLE_NAME + " DEFAULT VALUES";
        String insertItemSql = "INSERT INTO " + ITEM_TABLE_NAME + " (order_id, unit_price, quantity, type) VALUES (?, ?, ?, ?)";
        try (Connection connection = Database.getConnection();
             PreparedStatement orderStmt = connection.prepareStatement(insertOrderSql, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement itemStmt = connection.prepareStatement(insertItemSql)) {
            orderStmt.executeUpdate();
            try (ResultSet generatedKeys = orderStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int orderId = generatedKeys.getInt(1);
                    for (FoodItem item : order.getItems()) {
                        itemStmt.setInt(1, orderId);
                        itemStmt.setDouble(2, item.getUnitPrice());
                        itemStmt.setInt(3, item.getQuantity());
                        itemStmt.setString(4, item.getClass().getSimpleName());
                        itemStmt.addBatch();
                    }
                    itemStmt.executeBatch();
                }
            }
        }
    }

    @Override
    public void updateOrder(int orderId, Order updatedOrder) throws SQLException {
        // Delete existing items for this order
        String deleteItemsSql = "DELETE FROM " + ITEM_TABLE_NAME + " WHERE order_id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement deleteStmt = connection.prepareStatement(deleteItemsSql)) {
            deleteStmt.setInt(1, orderId);
            deleteStmt.executeUpdate();
        }

        // Add updated items
        String insertItemSql = "INSERT INTO " + ITEM_TABLE_NAME + " (order_id, unit_price, quantity, type) VALUES (?, ?, ?, ?)";
        try (Connection connection = Database.getConnection();
             PreparedStatement insertStmt = connection.prepareStatement(insertItemSql)) {
            for (FoodItem item : updatedOrder.getItems()) {
                insertStmt.setInt(1, orderId);
                insertStmt.setDouble(2, item.getUnitPrice());
                insertStmt.setInt(3, item.getQuantity());
                insertStmt.setString(4, item.getClass().getSimpleName());
                insertStmt.addBatch();
            }
            insertStmt.executeBatch();
        }
    }

    @Override
    public void deleteOrder(int orderId) throws SQLException {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE order_id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            stmt.executeUpdate();
        }
    }
}
