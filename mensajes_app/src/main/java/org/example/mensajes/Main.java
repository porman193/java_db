package org.example.mensajes;

import org.example.mensajes.data.DbConnection;
import org.example.mensajes.service.MensajeService;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("Aplicación de Mensajes");
            System.out.println("1. Crear mensaje");
            System.out.println("2. Listar mensajes");
            System.out.println("3. Editar mensaje");
            System.out.println("4. Eliminar mensaje");
            System.out.println("5. Salir");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    MensajeService.createMessage();
                    break;
                case 2:
                    MensajeService.getAllMessages();
                    break;
                case 3:
                    MensajeService.updateMessage();
                    break;
                case 4:
                    MensajeService.deleteMessageById();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }

        } while (opcion != 5);

        scanner.close();
    }
}