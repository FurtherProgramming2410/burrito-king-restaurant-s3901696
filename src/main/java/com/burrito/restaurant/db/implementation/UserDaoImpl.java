package com.burrito.restaurant.db.implementation;

import com.burrito.restaurant.db.DbConnection;
import com.burrito.restaurant.db.UserDao;
import com.burrito.restaurant.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class UserDaoImpl implements UserDao {
    private final String TABLE_NAME = "users";
    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public UserDaoImpl() {
        try (Connection connection = DbConnection.getConnection(); Statement stmt = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "username VARCHAR(50) NOT NULL UNIQUE," + "password VARCHAR(50) NOT NULL," +
                    "first_name VARCHAR(50)," + "last_name VARCHAR(50)," + "is_vip BOOLEAN DEFAULT FALSE," +
                    "email VARCHAR(50)," + "points INTEGER DEFAULT 0)";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            logger.error("Error while setting up " + this.getClass().getSimpleName(), e);
        }
    }

    @Override
    public User getById(int id) throws SQLException {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
        try (Connection connection = DbConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToUser(rs);
            }
        }
        return null;
    }

    @Override
    public User getByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE username = ?";
        try (Connection connection = DbConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToUser(rs);
            }
        }
        return null;
    }

    @Override
    public User login(String username, String password) throws SQLException {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE username = ? AND password = ?";
        try (Connection connection = DbConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToUser(rs);
            }
        }
        return null;
    }

    @Override
    public User create(User user) throws SQLException {
        String sql = "INSERT INTO " + TABLE_NAME + " (username, password, first_name, last_name, is_vip, email, points) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DbConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getFirstName());
            stmt.setString(4, user.getLastName());
            stmt.setBoolean(5, user.isVip());
            stmt.setString(6, user.getEmail());
            stmt.setInt(7, user.getPoints());
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }
        return user;
    }

    @Override
    public User update(User user) throws SQLException {
        String sql = "UPDATE " + TABLE_NAME + " SET username = ?, password = ?, first_name = ?, last_name = ?, is_vip = ?, email = ?, points = ? WHERE id = ?";
        try (Connection connection = DbConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getFirstName());
            stmt.setString(4, user.getLastName());
            stmt.setBoolean(5, user.isVip());
            stmt.setString(6, user.getEmail());
            stmt.setInt(7, user.getPoints());
            stmt.setInt(8, user.getId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating user failed, no rows affected.");
            }
        }
        return user;
    }

    @Override
    public User deleteById(int id) throws SQLException {
        User user = getById(id);
        if (user == null) {
            throw new SQLException("User not found for ID: " + id);
        }
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
        try (Connection connection = DbConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
        return user;
    }

    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setVip(rs.getBoolean("is_vip"));
        user.setEmail(rs.getString("email"));
        user.setPoints(rs.getInt("points"));
        return user;
    }
}