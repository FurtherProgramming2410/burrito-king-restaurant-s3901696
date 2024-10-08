package com.burrito.restaurant.model;

import com.burrito.restaurant.dao.UserDao;
import com.burrito.restaurant.implementation.UserDaoImpl;

import java.sql.SQLException;

public class Model {

	private UserDao userDao;
	private User currentUser; 
	
	public Model() {
		userDao = new UserDaoImpl();
	}
	
	public void setup() throws SQLException {
		userDao.setup();
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
