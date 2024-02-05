package com.example.alarm_drugs.Clases;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HistoriaClinica {
    private List<Patologias> Patologias;
    @SerializedName("Medicamentos-Alegicos")
    private List<medicamentosA> medicamentos_a;
    private List<Recetas> Recetas;

    public HistoriaClinica(List<com.example.alarm_drugs.Clases.Patologias> patologias, List<medicamentosA> medicamentos_a, List<com.example.alarm_drugs.Clases.Recetas> recetas) {
        Patologias = patologias;
        this.medicamentos_a = medicamentos_a;
        Recetas = recetas;
    }

    public List<com.example.alarm_drugs.Clases.Patologias> getPatologias() {
        return Patologias;
    }

    public List<medicamentosA> getMedicamentos_a() {
        return medicamentos_a;
    }

    public List<com.example.alarm_drugs.Clases.Recetas> getRecetas() {
        return Recetas;
    }
}
