package com.example.alarm_drugs;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleService;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.alarm_drugs.Clases.Drugs;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RescheduleAlarmService extends LifecycleService {

    AlarmDrugs Alarm;


    AlarmDrugRepository repository;
    private static final String TAG = "RESCHEDULE";


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        super.onBind(intent);
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();
        this.repository = new AlarmDrugRepository(getApplication());
        final String CHANEL_IDX = "Foreground Service ID";
        NotificationChannel Chanel = new NotificationChannel(
                CHANEL_IDX,
                CHANEL_IDX,
                NotificationManager.IMPORTANCE_LOW
        );
        getSystemService(NotificationManager.class).createNotificationChannel(Chanel);
        Notification.Builder Noti = new Notification.Builder(this,CHANEL_IDX)
                .setContentText("Cumple con tu medicina ")
                .setAutoCancel(true)
                        .setContentTitle("Alarma app")
                                .setSmallIcon(R.drawable.ic_launcher_foreground);
        startForeground(102102010,Noti.build());
    }

    @SuppressLint("SuspiciousIndentation")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Bundle bundle = intent.getBundleExtra(getString(R.string.bundle_alarm_obj));

        if (bundle != null)
            Alarm = (AlarmDrugs) bundle.getSerializable(getString(R.string.arg_alarm_obj));

        if (Alarm != null) {
            repository.getAllAlarms().observe(this, new Observer<List<AlarmDrugs>>() {
                @Override
                public void onChanged(List<AlarmDrugs> alarmDrugs) {
                    for(int x=0; x<alarmDrugs.size(); x++){
                        if(alarmDrugs.get(x).getAlarmDrugsId()==Alarm.getAlarmDrugsId()){
                            Alarm.setCantidad(alarmDrugs.get(x).getCantidad()-1);
                        }
                    }
                }
            });
            upgrade(Alarm);

            if (Alarm.getCantidad() == 0) {
                cancelAlarm(Alarm);
            }else {
                ReSchedule(Alarm);

            }
        }

        return START_STICKY;
    }

    private void ReSchedule(AlarmDrugs alarm) {
        alarm.schedule(getApplicationContext());

    }

    public void upgrade(AlarmDrugs alarmDrugs){

        repository.upgrade(alarmDrugs);
        Log.e(TAG, alarmDrugs.getDrug().getNombre()+" update "  );


    }
    public void cancelAlarm(AlarmDrugs alarmx){

        alarmx.cancelAlarm(getApplicationContext());
        Log.e(TAG, alarmx.getDrug().getNombre()+" Cancel "  );

    }

}