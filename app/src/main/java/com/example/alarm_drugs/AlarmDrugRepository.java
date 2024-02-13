package com.example.alarm_drugs;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.loader.content.AsyncTaskLoader;

import com.example.alarm_drugs.Clases.Drugs;
import com.example.alarm_drugs.Clases.Recetas;
import com.example.alarm_drugs.Clases.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AlarmDrugRepository {
    private AlarmDrugsDao alarmDrugsDao;
    private LiveData<List<AlarmDrugs>> allAlarm;
    private LiveData<List<Drugs>> allDrugs;
    private  ExecutorService executor = Executors.newSingleThreadExecutor();


    public AlarmDrugRepository(Application application){
        AlarmDatabase database = AlarmDatabase.getInstance(application);
        alarmDrugsDao = database.AlarmDrugsDao();
        allAlarm= alarmDrugsDao.getAllAlarm();
        allDrugs= alarmDrugsDao.getAllDrugs();
    }

    public void insert(AlarmDrugs alarmDrugs){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                alarmDrugsDao.Insert(alarmDrugs);
            }
        });

    }

    public void insertDrug(Drugs drugs){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                alarmDrugsDao.InsertDrugs(drugs);
            }
        });

    }
    public void insertUser(User user){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                alarmDrugsDao.InsertUser(user);
            }
        });

    }
    public void insertReceta(Recetas receta){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                alarmDrugsDao.InsertReceta(receta);
            }
        });

    }


    public void upgrade(AlarmDrugs alarmDrugs){

        executor.execute(new Runnable() {
            @Override
            public void run() {
                alarmDrugsDao.Update(alarmDrugs);
            }
        });

    }

    public void delete(AlarmDrugs alarmDrugs){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                alarmDrugsDao.Delete(alarmDrugs);
            }
        });
    }

    public void deleteAllAlarm(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                alarmDrugsDao.deleteAllAlarm();
            }
        });
    }

    public LiveData<List<AlarmDrugs>> getAllAlarms (){
        return allAlarm;
    }

    public LiveData<List<Drugs>> getAllDrugs (){
        return allDrugs;
    }

    private static class UpdateAlarmDrugAsyncTask extends AsyncTask<AlarmDrugs, Void, Void>{

        private AlarmDrugsDao alarmDrugsDao;

        public UpdateAlarmDrugAsyncTask(AlarmDrugsDao alarmDrugsDao) {
            this.alarmDrugsDao = alarmDrugsDao;
        }

        @Override
        protected Void doInBackground(AlarmDrugs... alarmDrugs) {
            alarmDrugsDao.Update(alarmDrugs[0]);
            return null;
        }
    }

    private static class DeleteAlarmDrugAsyncTask extends AsyncTask<AlarmDrugs, Void, Void>{

        private AlarmDrugsDao alarmDrugsDao;

        public DeleteAlarmDrugAsyncTask(AlarmDrugsDao alarmDrugsDao) {
            this.alarmDrugsDao = alarmDrugsDao;
        }

        @Override
        protected Void doInBackground(AlarmDrugs... alarmDrugs) {
            alarmDrugsDao.Delete(alarmDrugs[0]);
            return null;
        }
    }

    private static class DeleteAllAlarmDrugAsyncTask extends AsyncTask<Void, Void, Void>{

        private AlarmDrugsDao alarmDrugsDao;

        public DeleteAllAlarmDrugAsyncTask(AlarmDrugsDao alarmDrugsDao) {
            this.alarmDrugsDao = alarmDrugsDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            alarmDrugsDao.deleteAllAlarm();
            return null;
        }
    }


}
