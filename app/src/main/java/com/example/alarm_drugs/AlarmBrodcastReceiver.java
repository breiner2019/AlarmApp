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

            if (bundle!=null)
                Alarm =(AlarmDrugs)bundle.getSerializable(context.getString(R.string.arg_alarm_obj));
                if(Alarm!=null) {

                        startAlarmService(context, Alarm);
                        rescheduleAlarmServicex(context, Alarm);




                }
        }
    }





    private void rescheduleAlarmServicex(Context context, AlarmDrugs alarm) {
        Intent intentService = new Intent(context, RescheduleAlarmService.class);

        Bundle bundle=new Bundle();

        bundle.putSerializable(context.getString(R.string.arg_alarm_obj),alarm);

        intentService.putExtra(context.getString(R.string.bundle_alarm_obj),bundle);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intentService);
        } else {
            context.startService(intentService);
        }

    }

    public void startAlarmService(Context context, AlarmDrugs alarmx){

        Intent intentService = new Intent(context, AlarmService.class);
        Bundle bundle=new Bundle();

        bundle.putSerializable(context.getString(R.string.arg_alarm_obj),alarmx);
        intentService.putExtra(context.getString(R.string.bundle_alarm_obj),bundle);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intentService);
        } else {
            context.startService(intentService);
        }


    }



}
