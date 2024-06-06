package com.burrito.restaurant.model;

import com.burrito.restaurant.db.UserDao;
import com.burrito.restaurant.db.implementation.UserDaoImpl;

import java.sql.SQLException;

public class UserDetails {

	private UserDao userDao;
	private User currentUser; 
	
	public UserDetails() {
		userDao = new UserDaoImpl();
	}

	public UserDao getUserDao() {
		return userDao;
	}
	
	public User getCurrentUser() {
		return this.currentUser;
	}
	
	public void setCurrentUser(User user) {
		currentUser = user;
	}

}
