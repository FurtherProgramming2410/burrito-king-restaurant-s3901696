package com.burrito.restaurant.db.restaurant;

import com.burrito.restaurant.db.DbConnection;
import com.burrito.restaurant.db.implementation.UserDaoImpl;
import com.burrito.restaurant.model.User;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {

    private static UserDaoImpl userDao;

    @BeforeAll
    static void setup() throws SQLException {
        userDao = new UserDaoImpl();
    }

    @AfterEach
    void cleanup() throws SQLException {
        try (Connection connection = DbConnection.getConnection();
             var stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM users");
        }
    }

    @Test
    void testCreateAndGetById() throws SQLException {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setVip(false);
        user.setEmail("testuser@example.com");
        user.setPoints(10);

        User createdUser = userDao.create(user);

        assertNotNull(createdUser.getId());
        assertEquals("testuser", createdUser.getUsername());

        User retrievedUser = userDao.getById(createdUser.getId());
        assertNotNull(retrievedUser);
        assertEquals("testuser", retrievedUser.getUsername());
        assertEquals("password", retrievedUser.getPassword());
    }

    @Test
    void testGetByUsername() throws SQLException {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setVip(false);
        user.setEmail("testuser@example.com");
        user.setPoints(10);

        userDao.create(user);

        User retrievedUser = userDao.getByUsername("testuser");
        assertNotNull(retrievedUser);
        assertEquals("testuser", retrievedUser.getUsername());
        assertEquals("password", retrievedUser.getPassword());
    }

    @Test
    void testLogin() throws SQLException {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setVip(false);
        user.setEmail("testuser@example.com");
        user.setPoints(10);

        userDao.create(user);

        User loggedInUser = userDao.login("testuser", "password");
        assertNotNull(loggedInUser);
        assertEquals("testuser", loggedInUser.getUsername());
    }

    @Test
    void testUpdate() throws SQLException {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setVip(false);
        user.setEmail("testuser@example.com");
        user.setPoints(10);

        User createdUser = userDao.create(user);
        createdUser.setPassword("newpassword");
        createdUser.setEmail("newemail@example.com");

        User updatedUser = userDao.update(createdUser);
        assertNotNull(updatedUser);
        assertEquals("newpassword", updatedUser.getPassword());
        assertEquals("newemail@example.com", updatedUser.getEmail());
    }

    @Test
    void testDeleteById() throws SQLException {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setVip(false);
        user.setEmail("testuser@example.com");
        user.setPoints(10);

        User createdUser = userDao.create(user);
        int userId = createdUser.getId();

        User deletedUser = userDao.deleteById(userId);
        assertNotNull(deletedUser);
        assertEquals("testuser", deletedUser.getUsername());

        User retrievedUser = userDao.getById(userId);
        assertNull(retrievedUser);
    }
}