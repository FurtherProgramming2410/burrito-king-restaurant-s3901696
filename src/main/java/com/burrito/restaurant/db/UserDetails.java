package com.burrito.restaurant.db;

import com.burrito.restaurant.model.User;

public class UserDetails {
    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        UserDetails.currentUser = currentUser;
    }
}
