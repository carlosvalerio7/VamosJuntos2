package com.example.vamosjuntos.Evento;


public class Evento {
    //VARIABLES DEL EVENTO
    private String nombreEvento;
    private String direccionEvento;
    private String descripcionEvento;
    private String fechaEvento;
    private String horaEvento;

    //CONSTRUCTOR CON SUS PARAMETROS DEL EVENTO
    public Evento(String nombreEvento, String direccionEvento, String descripcionEvento, String fechaEvento, String horaEvento) {
        this.nombreEvento = nombreEvento;
        this.direccionEvento = direccionEvento;
        this.descripcionEvento = descripcionEvento;
        this.fechaEvento = fechaEvento;
        this.horaEvento = horaEvento;
    }

    //GETTERS Y SETTERS PARA OBTENER Y MODIFICAR DATOS
    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getDireccionEvento() {
        return direccionEvento;
    }

    public void setDireccionEvento(String direccionEvento) {
        this.direccionEvento = direccionEvento;
    }

    public String getDescripcionEvento() {
        return descripcionEvento;
    }

    public void setDescripcionEvento(String descripcionEvento) {
        this.descripcionEvento = descripcionEvento;
    }

    public String getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(String fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getHoraEvento() {
        return horaEvento;
    }

    public void setHoraEvento(String horaEvento) {
        this.horaEvento = horaEvento;
    }

}
