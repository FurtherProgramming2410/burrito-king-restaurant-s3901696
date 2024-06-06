package com.burrito.restaurant.dao;

import com.burrito.restaurant.model.Restaurant;

import java.sql.SQLException;

public interface RestaurantDao {
    void setup() throws SQLException;

    Restaurant getRestaurant(String name) throws SQLException;

    void addRestaurant(Restaurant restaurant) throws SQLException;

    void updateRestaurant(String name, Restaurant updatedRestaurant) throws SQLException;

    void deleteRestaurant(String name) throws SQLException;
}
