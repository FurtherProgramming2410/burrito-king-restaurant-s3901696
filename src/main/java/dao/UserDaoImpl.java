package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.User;

public class UserDaoImpl implements UserDao {
    private final String TABLE_NAME = "users";

    public UserDaoImpl() {
    }

    @Override
    public void setup() throws SQLException {
        try (Connection connection = Database.getConnection();
             Statement stmt = connection.createStatement();) {
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (username VARCHAR(10) NOT NULL,"
                    + "password VARCHAR(8) NOT NULL,"
                    + "firstName VARCHAR(50) NOT NULL,"
                    + "lastName VARCHAR(50) NOT NULL,"
                    + "email VARCHAR(50),"
                    + "isVip BOOLEAN NOT NULL,"
                    + "credits INT NOT NULL,"
                    + "PRIMARY KEY (username))";
            stmt.executeUpdate(sql);
        }
    }

    @Override
    public User getUser(String username, String password) throws SQLException {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE username = ? AND password = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setFirstName(rs.getString("firstName"));
                    user.setLastName(rs.getString("lastName"));
                    user.setEmail(rs.getString("email"));
                    user.setVIP(rs.getBoolean("isVip"));
                    user.setCredits(rs.getInt("credits"));
                    return user;
                }
                return null;
            }
        }
    }

    @Override
    public User createUser(String username, String password, String firstName, String lastName) throws SQLException {
        String sql = "INSERT INTO users (username, password, firstName, lastName, isVip, credits) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, firstName);
            stmt.setString(4, lastName);
            stmt.setBoolean(5, false);
            stmt.setInt(6, 0);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return new User(username, password, firstName, lastName);
            }
            return null;
        }
    }

    @Override
    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET password = ?, firstName = ?, lastName = ?, email = ?, isVip = ?, credits = ? WHERE username = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getFirstName());
            stmt.setString(3, user.getLastName());
            stmt.setString(4, user.getEmail());
            stmt.setBoolean(5, user.isVIP());
            stmt.setInt(6, user.getCredits());
            stmt.setString(7, user.getUsername());
            stmt.executeUpdate();
        }
    }
}
