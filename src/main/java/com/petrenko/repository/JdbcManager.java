package com.petrenko.repository;

import lombok.SneakyThrows;

import java.sql.*;


public class JdbcManager {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/test?currentSchema=public";
    private static final String USER = "postgres";
    private static final String PASS = "root";

    private JdbcManager() {
    }

    @SneakyThrows
    public static Connection getConnection() {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    @SneakyThrows
    public static void sendStatementForUpdate(String stringSql) {
        final Connection connection = JdbcManager.getConnection();
        final Statement statement = connection.createStatement();
        statement.executeUpdate(stringSql);
        statement.close();
        connection.close();
    }

}
