package com.example.andres.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String URL ="jdbc:mysql://localhost:3306/project";
    private static final String USERNAME ="cursos";
    private static final String PASSWORD ="1234";

    private static BasicDataSource connection;

    private static void connect(){
        try{
           connection = new BasicDataSource();
           connection.setUrl(URL);
           connection.setUsername(USERNAME);
           connection.setPassword(PASSWORD);

           connection.setInitialSize(3);
           connection.setMinIdle(2);
           connection.setMaxIdle(10);
           connection.setMaxTotal(10);

           System.out.println("Connection successfully");
        } catch (Exception e) {
            System.out.println("Something went wrong: ");
            System.out.println(e.toString());
        }
    }


    public static Connection getConnection() throws SQLException {

        connect();
        return connection.getConnection();
    }
}
