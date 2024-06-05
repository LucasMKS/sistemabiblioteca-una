package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSQL {
    private static final String URL = "jdbc:mysql://localhost:3306/sistemabiblioteca";
    private static final String USER = "root"; 
    private static final String PASSWORD = "root"; 

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
    }
}
