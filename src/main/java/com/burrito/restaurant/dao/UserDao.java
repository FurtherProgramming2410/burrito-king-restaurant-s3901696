package com.burrito.restaurant.dao;


import com.burrito.restaurant.model.User;

import java.sql.SQLException;


/**
 * A data access object (DAO) is a pattern that provides an abstract interface 
 * to a database or other persistence mechanism. 
 * the DAO maps application calls to the persistence layer and provides some specific data operations 
 * without exposing details of the database. 
 */
public interface UserDao {
	void setup() throws SQLException;
	User getUser(String username, String password) throws SQLException;
	User createUser(String username, String password,String firstName,String lastName) throws SQLException;
	User updateUser (String username, String password,String firstName,String lastName) throws SQLException;
}
