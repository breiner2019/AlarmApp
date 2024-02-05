package com.example.alarm_drugs;


import static com.example.alarm_drugs.app.CHANNEL_ID;
import static com.google.android.material.internal.ContextUtils.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alarm_drugs.Clases.Drugs;
import com.example.alarm_drugs.Clases.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Escuchador{
    private TextView txtview;

    private static final String TAG = "MyActivity";
    private AlarmDrugs xx;
    public static final int LENGTH_SHORT = 0;

    public AlarmViewModel Alarmviewmodel;










    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AlarmDrugAdapter adapter = new AlarmDrugAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.recycleview);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);


        AlarmViewModel Alarmviewmodel = new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(AlarmViewModel.class);;
        Alarmviewmodel.getAllAlarm().observe(this, new Observer<List<AlarmDrugs>>() {
            @Override
            public void onChanged(List<AlarmDrugs> alarmDrugs) {


            }
        });
        Alarmviewmodel.getAllDrugs().observe(this, new Observer<List<Drugs>>() {
            @Override
            public void onChanged(List<Drugs> drugs) {


                adapter.setDrugs(drugs);

            }
        });
    }

    @Override
    public void Alarma(AlarmDrugs alarm) {
        AlarmViewModel Alarmviewmodel = new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(AlarmViewModel.class);;
        Alarmviewmodel.insert(alarm);
    }
}

