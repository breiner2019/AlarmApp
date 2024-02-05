package com.example.alarm_drugs;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.alarm_drugs.Clases.Drugs;
import com.example.alarm_drugs.Clases.Recetas;
import com.example.alarm_drugs.Clases.User;

import java.util.List;


public class AlarmViewModel extends AndroidViewModel {
    private AlarmDrugRepository  repository;
    private LiveData<List<AlarmDrugs>> allAlarm;
    private LiveData<List<Drugs>> allDrugs;

    public AlarmViewModel(@NonNull Application application) {
        super(application);
        repository = new AlarmDrugRepository(application);
        allAlarm = repository.getAllAlarms();
        allDrugs = repository.getAllDrugs();
    }
    public void insert(AlarmDrugs alarmdrugs){
        repository.insert(alarmdrugs);
    }
    public void insertDrugs(Drugs drugs){
        repository.insertDrug(drugs);
    }
    public void insertReceta(Recetas recetas){
        repository.insertReceta(recetas);
    }
    public void insertUser(User user){
        repository.insertUser(user);
    }
    public void update(AlarmDrugs alarmdrugs){
        repository.upgrade(alarmdrugs);
    }
    public void delete(AlarmDrugs alarmdrugs){
        repository.delete(alarmdrugs);
    }
    public void deleteallalarm(){
        repository.deleteAllAlarm();
    }
    public LiveData<List<AlarmDrugs>> getAllAlarm(){
        return allAlarm;
    }
    public LiveData<List<Drugs>> getAllDrugs(){
        return allDrugs;
    }
}
