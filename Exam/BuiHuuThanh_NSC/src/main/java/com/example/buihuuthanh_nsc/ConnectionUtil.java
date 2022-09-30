package com.example.buihuuthanh_nsc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static Connection connection;

    public synchronized static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/nsc_db";
        String user = "root";
        String pass = "";
        if (connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(url, user, pass);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }

        return connection;
    }
}
