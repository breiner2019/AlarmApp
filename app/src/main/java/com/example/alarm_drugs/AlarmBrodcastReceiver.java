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
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.alarm_drugs.Clases.Drugs;

public class AlarmBrodcastReceiver extends BroadcastReceiver {
    private static final String TAG = "Myapp";

    AlarmDrugs Alarm;
    Drugs Drug;
    private AlarmDrugsDao alarmDrugsDao;


    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            String toastText = String.format("Alarm Reboot");
            Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();

        }
        else {

            Bundle bundle=intent.getBundleExtra(context.getString(R.string.bundle_alarm_obj));
            Bundle bundlex=intent.getBundleExtra(context.getString(R.string.bundle_alarm_objx));

            if (bundle!=null&&bundlex!=null)
                Alarm =(AlarmDrugs)bundle.getSerializable(context.getString(R.string.arg_alarm_obj));
                Drug =(Drugs)bundlex.getSerializable(context.getString(R.string.arg_alarm_objx));
                String toastText = String.format("Alarm Received "+Alarm.getAlarmDrugsId()+ " "+Drug.getNombre());
                Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
                if(Alarm!=null) {

                        startAlarmService(context, Alarm, Drug);
                        rescheduleAlarmServicex(context, Alarm, Drug);




                }
        }
    }



    private void sd(Context context, AlarmDrugs alarm, Drugs drug) {
        Intent i = new Intent(context, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_MUTABLE);
        NotificationCompat.Builder buidel = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Es tiempo de tomar tu medicina " + drug.getNombre())
                .setContentText("Hola por favor recuerda que tomar tu medicina a tiempo es vital para el cumplimiento del tratamiento")
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setContentIntent(pendingIntent);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(22, buidel.build());
    }

    private void rescheduleAlarmServicex(Context context, AlarmDrugs alarm, Drugs drug) {
        Intent intentService = new Intent(context, RescheduleAlarmService.class);

        Bundle bundle=new Bundle();
        Bundle bundlex=new Bundle();

        bundle.putSerializable(context.getString(R.string.arg_alarm_obj),alarm);
        bundlex.putSerializable(context.getString(R.string.arg_alarm_objx),drug);
        intentService.putExtra(context.getString(R.string.bundle_alarm_obj),bundle);
        intentService.putExtra(context.getString(R.string.bundle_alarm_objx),bundlex);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intentService);
        } else {
            context.startService(intentService);
        }

    }

    public void startAlarmService(Context context, AlarmDrugs alarmx, Drugs drug){

        Intent intentService = new Intent(context, AlarmService.class);
        Bundle bundle=new Bundle();
        Bundle bundlex=new Bundle();

        bundle.putSerializable(context.getString(R.string.arg_alarm_obj),alarmx);
        bundlex.putSerializable(context.getString(R.string.arg_alarm_objx),drug);
        intentService.putExtra(context.getString(R.string.bundle_alarm_obj),bundle);
        intentService.putExtra(context.getString(R.string.bundle_alarm_objx),bundlex);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intentService);
        } else {
            context.startService(intentService);
        }


    }



}
