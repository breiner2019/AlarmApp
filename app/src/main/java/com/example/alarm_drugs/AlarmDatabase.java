package com.example.alarm_drugs;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.alarm_drugs.Clases.Drugs;
import com.example.alarm_drugs.Clases.Recetas;
import com.example.alarm_drugs.Clases.User;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Database(entities = {AlarmDrugs.class, User.class, Drugs.class, Recetas.class}, version = 1)
@TypeConverters({User.Converters.class})
public abstract class AlarmDatabase extends RoomDatabase {

    private static AlarmDatabase instance;
    private static final String TAG = "ooo";


    public abstract AlarmDrugsDao AlarmDrugsDao();

    public static synchronized AlarmDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AlarmDatabase.class, "Alarms_DataBase")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomcallback)
                    .build();
        }
        Log.e(TAG, "Grasa= 1");

        return instance;


    }

    private static RoomDatabase.Callback roomcallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);


           AlarmDrugsDao Dao = AlarmDatabase.instance.AlarmDrugsDao();

            ExecutorService executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://my-json-server.typicode.com/breiner2019/ApiMedica/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

            Call<List<User>> call = jsonPlaceHolderApi.getDrugs();

            call.enqueue(new retrofit2.Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    if (!response.isSuccessful()) {
                        Log.e(TAG, "Grasa= " + +response.code());
                        return;
                    }
                    List<User> Users = response.body();
                    for (User user : Users) {
                        String content = "";
                        content += "Nombre: " + user.getHistoriaClinica().getRecetas();


                        executor.execute(() -> {
                            //Background work here
                           Dao.InsertUser(user);
                            for (int i = 0; i < user.getHistoriaClinica().getRecetas().size(); i++) {
                                Dao.InsertReceta(user.getHistoriaClinica().getRecetas().get(i));
                                for (int a = 0; a < user.getHistoriaClinica().getRecetas().get(i).getMedicamentos().size(); a++) {
                                    Dao.InsertDrugs(user.getHistoriaClinica().getRecetas().get(i).getMedicamentos().get(a));
                                }
                            }

                            handler.post(() -> {
                                //UI Thread work here
                            });
                        });

                    }
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {
                    Log.e(TAG, "Grasa= " + t.getMessage());

                }
            });


        }
    };

}