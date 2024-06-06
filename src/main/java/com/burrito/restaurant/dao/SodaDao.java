package com.burrito.restaurant.dao;

import com.burrito.restaurant.model.Soda;

import java.sql.SQLException;

/**
 * Data access object interface for Soda.
 */
public interface SodaDao {
    void setup() throws SQLException;

    Soda getSoda(int sodaId) throws SQLException;

    void addSoda(Soda soda) throws SQLException;

    void updateSoda(int sodaId, Soda updatedSoda) throws SQLException;

    void deleteSoda(int sodaId) throws SQLException;
}
