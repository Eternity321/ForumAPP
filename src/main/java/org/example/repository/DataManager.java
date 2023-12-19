package org.example.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataManager {
    private static final String jdbcUrl = "jdbc:postgresql://localhost:5432/ForumAPP";
    private static final String user = "postgres";
    private static final String password = "ny208k05";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(jdbcUrl, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось установить соединение с базой данных.", e);
        }
    }
}
