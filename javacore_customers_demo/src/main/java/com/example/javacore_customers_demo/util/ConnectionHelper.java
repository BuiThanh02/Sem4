package com.example.javacore_customers_demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {

    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/javacore_customer_demo";

    private static final String USERNAME = "root";

    private static final String PASSWORD = "";

    private static Connection connection;

    public static Connection getConnection(){
        try{
            if (connection == null || connection.isClosed()){
                connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
