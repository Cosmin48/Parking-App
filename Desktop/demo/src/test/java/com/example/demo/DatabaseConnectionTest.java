package com.example.demo;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest extends DatabaseConnection{
    public Connection databaseLink;
    public Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/amitdb";
        String user = "root";
        String password = "FCBarcelona14";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;
    }

}