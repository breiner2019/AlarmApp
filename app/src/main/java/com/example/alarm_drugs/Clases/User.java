package com.example.alarm_drugs.Clases;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = false)
    private int id ;
    private String Nombre;
    private String Apellidos, Direccion;
    private int Edad;

    private String Telefono ;
    private Acudiente Acudiente;
    private HistoriaClinica HistoriaClinica;


    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public void setEdad(int edad) {
        Edad = edad;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public void setAcudiente(com.example.alarm_drugs.Clases.Acudiente acudiente) {
        Acudiente = acudiente;
    }

    public void setHistoriaClinica(com.example.alarm_drugs.Clases.HistoriaClinica historiaClinica) {
        HistoriaClinica = historiaClinica;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public String getDireccion() {
        return Direccion;
    }

    public int getEdad() {
        return Edad;
    }

    public String getTelefono() {
        return Telefono;
    }

    public com.example.alarm_drugs.Clases.Acudiente getAcudiente() {
        return Acudiente;
    }

    public com.example.alarm_drugs.Clases.HistoriaClinica getHistoriaClinica() {
        return HistoriaClinica;
    }

    public static class Converters {


        @TypeConverter
        public Acudiente fromStringAcudiente (String value) {
            return new Gson().fromJson(value, Acudiente.class);
        }

        @TypeConverter
        public String fromAcudiente (Acudiente Acudiente) {
            return new Gson().toJson(Acudiente);
        }

        @TypeConverter
        public HistoriaClinica fromStringHistoria (String value) {
            return new Gson().fromJson(value, HistoriaClinica.class);
        }

        @TypeConverter
        public String fromHistoria (HistoriaClinica HistoriaClinica) {
            return new Gson().toJson(HistoriaClinica);
        }
    }


}
