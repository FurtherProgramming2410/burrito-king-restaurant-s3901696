package com.burrito.restaurant.db;


import com.burrito.restaurant.model.User;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    List<User> getAll() throws SQLException;

    User getById(int id) throws SQLException;

    User getByUsername(String username) throws SQLException;

    User login(String username, String password) throws SQLException;

    User create(User user) throws SQLException;

    User update(User user) throws SQLException;

    User deleteById(int id) throws SQLException;

}
