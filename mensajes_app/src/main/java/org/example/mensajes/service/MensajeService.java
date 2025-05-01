package org.example.mensajes.service;

import org.example.mensajes.model.Mensaje;
import org.example.mensajes.util.MensajeDao;

import java.util.List;
import java.util.Scanner;

public class MensajeService {

    public static void createMessage(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el mensaje:");
        String message = scanner.nextLine();
        System.out.println("Ingrese el autor del mensaje:");
        String autor = scanner.nextLine();
        Mensaje newMessage = new Mensaje(message, null, autor);
        MensajeDao.createMessageDb(newMessage);
    };

    public static void getAllMessages(){
        try {
            List<Mensaje> messages = MensajeDao.getAllMessagesDb();
            if (messages == null || messages.isEmpty()) {
                System.out.println("No hay mensajes disponibles.");
                return;
            }
            System.out.println("Lista de mensajes:");
            System.out.println("------------------------------");
            messages.forEach(mensaje -> {
                System.out.println("ID: " + mensaje.getId());
                System.out.println("Mensaje: " + mensaje.getMensaje());
                System.out.println("Fecha: " + mensaje.getFecha());
                System.out.println("Autor: " + mensaje.getAutorMensaje());
                System.out.println("------------------------------");
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    };

    public static void  deleteMessageById(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el ID del mensaje a eliminar:");
        int id = scanner.nextInt();
        MensajeDao.deleteMessageByIdDb(id);
    };

    public static void updateMessage(){
        Scanner sanner = new Scanner(System.in);
        System.out.println("Ingrese el ID del mensaje a editar:");
        int id = sanner.nextInt();
        sanner.nextLine();
        System.out.println("Ingrese el nuevo mensaje:");
        String message = sanner.nextLine();
        Mensaje newMessage = new Mensaje();
        newMessage.setId(id);
        newMessage.setMensaje(message);
        MensajeDao.updateMessageDb(newMessage);
    };
}
