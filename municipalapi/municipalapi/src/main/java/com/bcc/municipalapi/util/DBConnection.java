package com.bcc.municipalapi.util;

// 3.1 Database connection class using JDBC
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // database URL structure: jdbc:mysql://host:port/databaseName
    private static final String URL = "jdbc:mysql://localhost:3306/municipal_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Queeneth@1";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // load the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // connect using DriverManager
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected successfully.");

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
        return conn; // return the Connection object
    }
}