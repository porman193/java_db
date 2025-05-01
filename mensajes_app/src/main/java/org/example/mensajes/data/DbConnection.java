package org.example.mensajes.data;

import java.sql.Connection;

public class DbConnection {
    private static final  String URL = "jdbc:mysql://localhost:3306/mensajes_app";
    private  static  final  String USER = "cursos";
    private  static  final  String PASSWORD = "1234";
    private static Connection connection = null;

    public static  Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = java.sql.DriverManager.getConnection(URL,USER, PASSWORD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
