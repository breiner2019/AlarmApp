package com.example.alarm_drugs.Clases;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.alarm_drugs.AlarmDrugs;

public class AlarmAndDrugs {

   @Embedded private AlarmDrugs alarmDrugs;
   @Relation(parentColumn = "Id_drugs",entityColumn = "Id")
   private Drugs medicamento;

   public void setAlarmDrugs(AlarmDrugs alarmDrugs) {
      this.alarmDrugs = alarmDrugs;
   }

   public void setMedicamento(Drugs medicamento) {
      this.medicamento = medicamento;
   }
}
