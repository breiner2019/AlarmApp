package com.example.alarm_drugs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.NumberPicker;

public class add_alarm extends AppCompatActivity {
    private NumberPicker hournumberpicker;
    private NumberPicker minutenumberpicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);

        hournumberpicker = findViewById(R.id.numberpciker_hour);
        minutenumberpicker = findViewById(R.id.numberpciker_minute);

        hournumberpicker.setMaxValue(24);
        hournumberpicker.setMinValue(0);

        minutenumberpicker.setMaxValue(59);
        minutenumberpicker.setMinValue(0);


    }
    public void savedata(){

        setResult();
        finish();
    }
}