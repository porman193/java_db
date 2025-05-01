package org.example.mensajes.model;

public class Mensaje {
    private Integer id;
    private String mensaje;
    private String fecha;
    private String autorMensaje;

    public Mensaje(String mensaje, String fecha, String autorMensaje) {
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.autorMensaje = autorMensaje;
    }

    public Mensaje() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getAutorMensaje() {
        return autorMensaje;
    }

    public void setAutorMensaje(String autorMensaje) {
        this.autorMensaje = autorMensaje;
    }
}
