package com.example.alarm_drugs;

import static android.app.AlarmManager.RTC_WAKEUP;

import static androidx.media3.common.MediaLibraryInfo.TAG;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import com.example.alarm_drugs.Clases.Acudiente;
import com.example.alarm_drugs.Clases.Drugs;
import com.example.alarm_drugs.Clases.HistoriaClinica;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

@Entity(tableName = "Alarm_Drugs_Table")
public class AlarmDrugs implements Serializable {

    //Creation vars
    @PrimaryKey 
    private int AlarmDrugsId;
    private int cantidad;
    private int Id_drugs;
    private boolean Started;
    long time;
    long timestart;





    public void setTime(long time) {
        this.time = 600*time;
    }



    public void setTimestart(long timestart) {
        this.timestart = System.currentTimeMillis()+timestart;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setAlarmDrugsId(int alarmDrugsId) {
        AlarmDrugsId = alarmDrugsId;
    }



    public void setId_drugs(int id_drugs) {
        Id_drugs = id_drugs;
    }

    public void setStarted(boolean started) {
        Started = started;
    }




    public int getAlarmDrugsId() {
        return AlarmDrugsId;
    }


    public long getTime() {
        return time;
    }
    public long getTimestart() {
        return timestart;
    }
    public int getId_drugs() {
        return Id_drugs;
    }

    public boolean isStarted() {
        return Started;
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

    public  void schedule(Context context, Drugs drugs){

        setTimestart(time);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmBrodcastReceiver.class);
        Bundle bundle=new Bundle();
        Bundle bundlex=new Bundle();
        bundle.putSerializable(context.getString(R.string.arg_alarm_obj),this);
        bundle.putSerializable(context.getString(R.string.arg_alarm_objx),drugs);
        intent.putExtra(context.getString(R.string.bundle_alarm_obj),bundle);
        intent.putExtra(context.getString(R.string.bundle_alarm_objx),bundlex);
        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, AlarmDrugsId, intent, PendingIntent.FLAG_MUTABLE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,System.currentTimeMillis() + time,alarmPendingIntent);



    }




}
