package org.example.mensajes;

import org.example.mensajes.data.DbConnection;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        DbConnection dbConnection = new DbConnection();

        try (Connection connection = dbConnection.getConnection();){

        } catch (Exception e) {
            System.out.println("Error al conectar a la base de datos");
            e.printStackTrace();
        }



    }
}