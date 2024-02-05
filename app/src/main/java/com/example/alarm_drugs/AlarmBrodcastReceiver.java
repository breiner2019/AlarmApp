package com.example.alarm_drugs;

import static com.example.alarm_drugs.app.CHANNEL_ID;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.alarm_drugs.Clases.Drugs;

public class AlarmBrodcastReceiver extends BroadcastReceiver {

    AlarmDrugs Alarm;
    Drugs Drug;


    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            String toastText = String.format("Alarm Reboot");
            Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();

        }
        else {
            Bundle bundle=intent.getBundleExtra(context.getString(R.string.bundle_alarm_obj));
            Bundle bundlex=intent.getBundleExtra(context.getString(R.string.bundle_alarm_objx));
            if (bundle!=null||bundlex!=null)
                Alarm =(AlarmDrugs)bundle.getSerializable(context.getString(R.string.arg_alarm_obj));
                Drug =(Drugs)bundle.getSerializable(context.getString(R.string.arg_alarm_objx));
            String toastText = String.format("Alarm Received");
            Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
            if(Alarm!=null) {
                    startAlarmService(context, Alarm, Drug, intent);
            }
        }
    }
    public void startAlarmService(Context context, AlarmDrugs alarmx, Drugs drug, Intent intent){
        Toast.makeText(context,"Llego la alarm al broad",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);

        NotificationCompat.Builder buidel = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Es tiempo de tomar tu medicina "+drug.getNombre())
                .setContentText("Hola por favor recuerda que tomar tu medicina a tiempo es vital para el cumplimiento del tratamiento")
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(22, buidel.build());

    }


}
