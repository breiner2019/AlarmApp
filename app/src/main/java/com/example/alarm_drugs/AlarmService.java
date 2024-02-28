package com.example.alarm_drugs;

import static com.example.alarm_drugs.app.CHANNEL_ID;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.LifecycleService;
import androidx.lifecycle.Observer;

import com.example.alarm_drugs.Clases.Drugs;

import java.io.IOException;
import java.util.List;


public class AlarmService  extends Service {

    AlarmDrugs Alarm;



    @Override
    public void onCreate() {
        super.onCreate();
        final String CHANEL_IDX = "Foreground Service ID";
        NotificationChannel Chanel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Chanel = new NotificationChannel(
                    CHANEL_IDX,
                    CHANEL_IDX,
                    NotificationManager.IMPORTANCE_LOW
            );
            getSystemService(NotificationManager.class).createNotificationChannel(Chanel);
            Notification.Builder Noti = new Notification.Builder(this,CHANEL_IDX)
                    .setContentText("Service notification")
                    .setContentTitle("Service")
                    .setSmallIcon(R.drawable.ic_launcher_background);
            startForeground(102103310,Noti.build());
        }


    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        super.onStartCommand(intent, flags, startId);

        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            String toastText = String.format("Alarm Reboot");
            Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();

        } else {
            Bundle bundle = intent.getBundleExtra(getString(R.string.bundle_alarm_obj));

            if (bundle != null )
                Alarm = (AlarmDrugs) bundle.getSerializable(getString(R.string.arg_alarm_obj));
            String toastText = String.format("Alarm Received en el service");
            Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
            if (Alarm != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    String CHANNEL_IDx = Alarm.getDrug().getNombre();

                    CharSequence name = "x";
                    String description = "x";
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel channel = new NotificationChannel(CHANNEL_IDx, name, importance);
                channel.setDescription(description);

                NotificationManager notificationManager = getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);

                Intent i = new Intent(this, RescheduleAlarmService.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_MUTABLE);

                NotificationCompat.Builder buidel = new NotificationCompat.Builder(this, CHANNEL_IDx)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("Breiner tu medicina " + Alarm.getDrug().getNombre())
                        .setContentText("Recuerda que tomar tu medicina a tiempo es vital para el cumplimiento del tratamiento")
                        .setAutoCancel(true)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setContentIntent(pendingIntent);
                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
                notificationManagerCompat.notify(Alarm.getAlarmDrugsId(), buidel.build());
            }
            }
        }


        return START_STICKY;
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
