package org.example.airportmanagementsystem.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    private static final String url = "jdbc:postgresql://localhost:5432/Airport_Management";
    private static final String user = "postgres";
    private static final String pass = "java";
    private static final Connection con;

    static {
        try {
            con = DriverManager.getConnection(url,user,pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Connect() {
    }
    public static Connection getConnection(){

        return con;
    }
}
