package br.com.rentcar.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/rentcardb", "postgres", "postgres");
            return con;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
