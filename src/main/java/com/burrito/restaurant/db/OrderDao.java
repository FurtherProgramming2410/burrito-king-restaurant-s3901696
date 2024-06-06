package com.burrito.restaurant.db;

import com.burrito.restaurant.model.Order;
import java.sql.SQLException;

public interface OrderDao {

    Order getOrder(int orderId) throws SQLException;

    void addOrder(Order order) throws SQLException;

    void updateOrder(int orderId, Order updatedOrder) throws SQLException;

    void deleteOrder(int orderId) throws SQLException;
}
