package com.example.alarm_drugs.Clases;

public class Patologias {
    private String Nombre,Descripcion;

    public Patologias(String nombre, String descripcion) {
        Nombre = nombre;
        Descripcion = descripcion;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }
}
