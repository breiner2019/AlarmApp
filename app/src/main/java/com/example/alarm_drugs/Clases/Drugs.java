package com.example.alarm_drugs.Clases;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "drugs_table")
public class Drugs implements Serializable {
    @PrimaryKey
    private int Id;
    private String Nombre;
    private String Tipo;
    private String Cantidad;
    private int Tiempo;
    private int Total;
    private String info;
    private String Codigo;
    private String Contraindicacciones;

    private int id_receta;

    public int getId_receta() {
        return id_receta;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public void setCantidad(String cantidad) {
        Cantidad = cantidad;
    }

    public void setTiempo(int tiempo) {
        Tiempo = tiempo;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public void setContraindicacciones(String contraindicacciones) {
        Contraindicacciones = contraindicacciones;
    }

    public void setId_receta(int id_receta) {
        this.id_receta = id_receta;
    }

    public String getContraindicacciones() {
        return Contraindicacciones;
    }

    public String getCodigo() {
        return Codigo;
    }

    public String getInfo() {
        return info;
    }

    public int getTotal() {
        return Total;
    }

    public int getTiempo() {
        return Tiempo;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public String getTipo() {
        return Tipo;
    }

    public String getNombre() {
        return Nombre;
    }

    public int getId() {
        return Id;
    }


}
