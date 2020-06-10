package com.example.vamosjuntos.Coche;

public class Coche {
    //VARIABLES DEL COCHE
    private int plazas;
    private String direccionSalida;
    private String telefono;

    //CONSTRUCTOR CON SUS PARAMETROS DEL COCHE
    public Coche(int plazas, String direccionSalida, String telefono) {
        this.plazas = plazas;
        this.direccionSalida = direccionSalida;
        this.telefono = telefono;
    }

    //GETTERS Y SETTERS PARA OBTENER Y MODIFICAR DATOS
    public int getPlazas() {
        return plazas;
    }

    public void setPlazas(int plazas) {
        this.plazas = plazas;
    }

    public String getDireccionSalida() {
        return direccionSalida;
    }

    public void setDireccionSalida(String direccionSalida) {
        this.direccionSalida = direccionSalida;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
