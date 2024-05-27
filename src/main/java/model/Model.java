package model;

import java.sql.SQLException;
import dao.UserDao;
import dao.UserDaoImpl;

public class Model {
    private UserDao userDao;
    private User currentUser;

    public Model() {
        userDao = new UserDaoImpl();
    }

    public void setup() throws SQLException {
        userDao.setup();
    }

    // Getters and Setters
    public UserDao getUserDao() { return userDao; }

    public User getCurrentUser() { return currentUser; }

    public void setCurrentUser(User user) { currentUser = user; }
    
    // Additional Methods
    public boolean updateUserProfile(String firstName, String lastName, String password) throws SQLException {
        if (currentUser != null) {
            currentUser.setFirstName(firstName);
            currentUser.setLastName(lastName);
            currentUser.setPassword(password);
            userDao.updateUser(currentUser);
            return true;
        }
        return false;
    }

    public boolean upgradeToVIP(String email) throws SQLException {
        if (currentUser != null && email != null && !email.isEmpty()) {
            currentUser.setVIP(true);
            currentUser.setEmail(email);
            userDao.updateUser(currentUser);
            return true;
        }
        return false;
    }
}
