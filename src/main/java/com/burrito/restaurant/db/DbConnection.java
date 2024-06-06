package com.burrito.restaurant.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static String databaseUrl = "jdbc:sqlite:application.db";

    public static void setDatabaseUrl(String url) {
        databaseUrl = url;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseUrl);
    }
}