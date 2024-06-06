package com.burrito.restaurant.model;

import com.burrito.restaurant.old.Order;

import java.util.List;

public class User {
	private String username;
	private String password;
	private String email;
	private int points;
	private List<Order> orderList;

	public User() {
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
