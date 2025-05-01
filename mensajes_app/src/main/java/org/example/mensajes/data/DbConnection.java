package org.example.mensajes.data;

import java.sql.Connection;

public class DbConnection {
    private static final  String URL = "jdbc:mysql://localhost:3306/mensajes_app";
    private  static  final  String USER = "cursos";
    private  static  final  String PASSWORD = "1234";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = java.sql.DriverManager.getConnection(URL,USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos" + URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
