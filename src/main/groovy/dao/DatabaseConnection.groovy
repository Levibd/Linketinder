package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "admin";
    private static final String PASSWORD = "password123";

    static Connection getConnection() {
        try{
            return  DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            println "❌ Erro ao conectar no banco: " + e.getMessage()
            throw e
        }
    }

}
