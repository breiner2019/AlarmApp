package com.example.alarm_drugs;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alarm_drugs.Clases.Drugs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class AlarmDrugAdapter extends RecyclerView.Adapter<AlarmDrugAdapter.ViewHolder> {
    List<AlarmDrugs> mListAlarms = new ArrayList<>();

    private static final String TAG = "Adapter";
    private Escuchador escuchador;

    public AlarmDrugAdapter( Escuchador escuchador) {
       ;
        this.mListAlarms = mListAlarms;
        this.escuchador = escuchador;
    }


    public void setAlarms (List<AlarmDrugs> Alarms){
        this.mListAlarms = Alarms;
        notifyDataSetChanged();
    }
    public void DeleteItem(int position){
        notifyItemRemoved(position);
    }




    @NonNull
    @Override
    public AlarmDrugAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.card_view, parent,false);
        return new AlarmDrugAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmDrugAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        AlarmDrugs CurrentAlarm = mListAlarms.get(position);
        holder.nombretxtview.setText(CurrentAlarm.getDrug().getNombre());
        holder.codetxtview.setText(CurrentAlarm.getDrug().getCodigo());
        holder.grtxtview.setText(CurrentAlarm.getDrug().getCantidad());

        if(CurrentAlarm.isStarted()){
            holder.Actived();
            holder.CountTimer(CurrentAlarm.getTimestart()-System.currentTimeMillis());
            holder.stateimage.setImageResource(R.drawable.circle);
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a ");
            String currentDateAndTime = sdf.format(new Date(CurrentAlarm.getTimestart()));
            holder.timestart.setText("Hora: "+currentDateAndTime);
            holder.timetxtview.setVisibility(View.VISIBLE);
            holder.SecTxtview.setVisibility(View.VISIBLE);
            holder.timestart.setVisibility(View.VISIBLE);
            holder.Inactivatxtview.setVisibility(View.INVISIBLE);
            switch (CurrentAlarm.getDrug().getTipo()) {
                case "inyeccion":
                    holder.imagenview.setImageResource(R.drawable.injection);
                    break;
                case "GEL":
                    holder.imagenview.setImageResource(R.drawable.tube);
                    break;
                case "pastillas":
                    holder.imagenview.setImageResource(R.drawable.pill_1);
                    break;
            }


        }else{
            holder.noActived();
            holder.Inactivatxtview.setVisibility(View.VISIBLE);
            holder.timetxtview.setVisibility(View.INVISIBLE);
            holder.SecTxtview.setVisibility(View.INVISIBLE);
            holder.timestart.setVisibility(View.INVISIBLE);
            holder.stateimage.setImageResource(R.drawable.circle_red);
            switch (CurrentAlarm.getDrug().getTipo()) {
                case "inyeccion":
                    holder.imagenview.setImageResource(R.drawable.injection_noactive);
                    break;
                case "GEL":
                    holder.imagenview.setImageResource(R.drawable.tube);
                    break;
                case "pastillas":
                    holder.imagenview.setImageResource(R.drawable.pill_noactive);
                    break;
            }


        }
        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                escuchador.DeleteAlarma(CurrentAlarm,position);
                DeleteItem(position);
                return false;
            }
        });


        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), CurrentAlarm.getDrug().getNombre(),Toast.LENGTH_SHORT).show();
                CurrentAlarm.setStarted(true);
                CurrentAlarm.schedule(v.getContext());
                escuchador.UpdateAlarma(CurrentAlarm,position);
                holder.CountTimer(CurrentAlarm.getTimestart()-System.currentTimeMillis());
                holder.Inactivatxtview.setVisibility(View.INVISIBLE);

            }
        });








    }

    @Override
    public int getItemCount() {
        return mListAlarms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombretxtview;
        TextView epstxtview;
        TextView timetxtview;
        TextView Inactivatxtview;
        TextView SecTxtview;
        TextView timestart;
        TextView codetxtview;
        TextView grtxtview;
        CardView card;
        ImageView imagenview;
        ImageView stateimage;

        CountDownTimer timer;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.cardview);
            nombretxtview = itemView.findViewById(R.id.nametxtview);
            timetxtview = itemView.findViewById(R.id.timetxtview2);
            SecTxtview = itemView.findViewById(R.id.secstxtview2);
            codetxtview = itemView.findViewById(R.id.codetxtview);
            grtxtview = itemView.findViewById(R.id.grtxtview);
            Inactivatxtview = itemView.findViewById(R.id.Estado_Inactivo_textview);
            imagenview =  itemView.findViewById(R.id.image);
            stateimage = itemView.findViewById(R.id.image_stated);
            timestart=itemView.findViewById(R.id.timestar_textview);
            epstxtview=itemView.findViewById(R.id.epstextview);
        }

        public void CountTimer(Long Timestar){
            SecTxtview.setVisibility(View.VISIBLE);

            new CountDownTimer(Timestar, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    int seconds = (int) (millisUntilFinished / 1000) % 60 ;
                    int minutes = (int) ((millisUntilFinished / (1000*60)) % 60);
                    int hours   = (int) ((millisUntilFinished / (1000*60*60)) % 24);
                    timetxtview.setText( "Restante: "+hours+ ":"+minutes);
                    SecTxtview.setText( String.valueOf(seconds));



                }

                @Override
                public void onFinish() {


                }
            }.start();
        }
        public void noActived (){

            nombretxtview.setTextColor(Color.rgb(170,170,170));
            timetxtview.setTextColor(Color.rgb(170,170,170));
            Inactivatxtview.setTextColor(Color.rgb(170,170,170));
            SecTxtview.setTextColor(Color.rgb(170,170,170));
            timestart.setTextColor(Color.rgb(170,170,170));
            codetxtview.setTextColor(Color.rgb(170,170,170));
            grtxtview.setTextColor(Color.rgb(170,170,170));
            epstxtview.setTextColor(Color.rgb(170,170,170));
        }
        public void Actived (){
            nombretxtview.setTextColor(Color.rgb(255,255,255));
            timetxtview.setTextColor(Color.rgb(255,255,255));
            Inactivatxtview.setTextColor(Color.rgb(255,255,255));
            SecTxtview.setTextColor(Color.rgb(255,255,255));
            timestart.setTextColor(Color.rgb(255,255,255));
            codetxtview.setTextColor(Color.rgb(255,255,255));
            grtxtview.setTextColor(Color.rgb(255,255,255));
            epstxtview.setTextColor(Color.rgb(255,255,255));
        }

    }
}
