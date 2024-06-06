package com.burrito.restaurant.dao;


import com.burrito.restaurant.model.User;

import java.sql.*;


public class UserDaoImpl implements UserDao {
	private final String TABLE_NAME = "users";

	public UserDaoImpl() {

	}

	@Override
	public void setup() throws SQLException {
		try (Connection connection = Database.getConnection();
			 Statement stmt = connection.createStatement();) {
			String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
					"username VARCHAR(10) NOT NULL," +
					"password VARCHAR(8) NOT NULL," +
					"first_name VARCHAR(10)," +
					"last_name VARCHAR(10)," + // Comma added here
					"PRIMARY KEY (username))";
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
					return user;
				}
				return null;
			} 
		}
	}

	@Override
	public User createUser(String username, String password,String firstname,String lastname) throws SQLException {
		String sql = "INSERT INTO " + TABLE_NAME + " (username, password, first_name, last_name) VALUES (?, ?, ?, ?)";
		try (Connection connection = Database.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql);) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, firstname);
			stmt.setString(4, lastname);

			stmt.executeUpdate();
			return new User(username, password);
		} 
	}
}
