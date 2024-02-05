package com.example.alarm_drugs.Clases;

public class Acudiente {
    private int id;

    private String Telefono, Nombre,Correo;

    public Acudiente(int id, String telefono, String nombre, String correo) {
        this.id = id;
        Telefono = telefono;
        Nombre = nombre;
        Correo = correo;
    }

    public int getId() {
        return id;
    }

    public String getTelefono() {
        return Telefono;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getCorreo() {
        return Correo;
    }
}
