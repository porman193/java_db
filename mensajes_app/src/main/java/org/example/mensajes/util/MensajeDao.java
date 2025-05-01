package org.example.mensajes.util;

import org.example.mensajes.data.DbConnection;
import org.example.mensajes.model.Mensaje;

import java.sql.*;
import java.util.List;

public class MensajeDao {
    public static void createMessageDb(Mensaje message){
        try(Connection connection = DbConnection.getConnection()) {
            String query = "INSERT INTO mensajes (mensaje, fecha_mensaje, autor_mensaje) VALUES (?, CURRENT_TIMESTAMP, ?)";
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, message.getMensaje());
                statement.setString(2, message.getAutorMensaje());
                statement.executeUpdate();
                System.out.println("Mensaje creado correctamente");
            }catch (Exception e){
                e.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    };

    public static List<Mensaje> getAllMessagesDb()  {
        try(Connection connection = DbConnection.getConnection()){
            try(Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM mensajes");)
            {
                List<Mensaje> messages = new java.util.ArrayList<>();
                while (resultSet.next()){
                    Mensaje message = new Mensaje();
                    message.setId(resultSet.getInt("id"));
                    message.setMensaje(resultSet.getString("mensaje"));
                    message.setFecha(resultSet.getString("fecha_mensaje"));
                    message.setAutorMensaje(resultSet.getString("autor_mensaje"));
                    messages.add(message);
                }
                return messages;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    };

    public static void  deleteMessageByIdDb(int id){
        try(Connection connection = DbConnection.getConnection()){
            String query = "DELETE FROM mensajes WHERE id = ?";
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, id);
                int rowsAffected=statement.executeUpdate();
                if (rowsAffected == 0) {
                    System.out.println("No se encontró el mensaje con ID: " + id);
                } else {
                    System.out.println("Mensaje eliminado correctamente");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar el mensaje: " + e.getMessage());
        }
    };

    public static void updateMessageDb(Mensaje message){
        try(Connection connection = DbConnection.getConnection()){
            String query = "UPDATE mensajes SET mensaje = ?, fecha_mensaje = CURRENT_TIMESTAMP, autor_mensaje = ? WHERE id = ?";
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, message.getMensaje());
                statement.setString(2, message.getAutorMensaje());
                statement.setInt(3, message.getId());
                int rowsAffected=statement.executeUpdate();
                if (rowsAffected == 0) {
                    System.out.println("No se encontró el mensaje con ID: " + message.getId());
                } else {
                    System.out.println("Mensaje actualizado correctamente");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar el mensaje: " + e.getMessage());
        }
    };
}
