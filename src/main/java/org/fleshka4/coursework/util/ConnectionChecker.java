package org.fleshka4.coursework.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionChecker {
    public static void main(String[] args) {
        try(final Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "password")) {
            if (connection.isValid(1)) {
                System.out.println("Connected successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
