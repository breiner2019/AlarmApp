package com.example.alarm_drugs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.alarm_drugs.Clases.AlarmAndDrugs;
import com.example.alarm_drugs.Clases.Drugs;
import com.example.alarm_drugs.Clases.Recetas;
import com.example.alarm_drugs.Clases.User;

import java.util.List;

@Dao
public interface AlarmDrugsDao {

    @Insert
    void Insert(AlarmDrugs alarmDrugs);

    @Insert
    void InsertDrugs(Drugs drugs);

    @Insert
    void InsertUser(User user);

    @Insert
    void InsertReceta(Recetas recetas);

    @Update
    void Update(AlarmDrugs alarmDrugs);

    @Delete
    void Delete(AlarmDrugs alarmDrugs);

    @Query("DELETE FROM Alarm_Drugs_Table")
    void deleteAllAlarm();

    @Query("SELECT * FROM Alarm_Drugs_Table")
    LiveData<List<AlarmDrugs>> getAllAlarm();

    @Query("SELECT * FROM drugs_table")
    LiveData<List<Drugs>> getAllDrugs();

   /* @Query("SELECT * FROM Alarm_Drugs_Table WHERE Id_drugs= id_drugs" )
    LiveData<List<AlarmAndDrugs>> getAllAlarmDrug(String id_drugs);*/
}
