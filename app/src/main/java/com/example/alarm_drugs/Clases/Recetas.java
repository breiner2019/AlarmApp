package com.example.alarm_drugs.Clases;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;
@Entity(tableName = "receta_table")
public class Recetas {
    @PrimaryKey
    private int id;
    private String Fecha,Motivo,Diagnostico,estado;
    @Ignore
    private List<Drugs> Medicamentos;

    public void setId(int id) {
        this.id = id;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public void setMotivo(String motivo) {
        Motivo = motivo;
    }

    public void setDiagnostico(String diagnostico) {
        Diagnostico = diagnostico;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setMedicamentos(List<Drugs> medicamentos) {
        Medicamentos = medicamentos;
    }

    public int getId() {
        return id;
    }

    public String getFecha() {
        return Fecha;
    }

    public String getMotivo() {
        return Motivo;
    }

    public String getDiagnostico() {
        return Diagnostico;
    }

    public String getEstado() {
        return estado;
    }

    public List<Drugs> getMedicamentos() {
        return Medicamentos;
    }
}

