package com.example.alarm_drugs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alarm_drugs.Clases.Drugs;

import java.util.ArrayList;
import java.util.List;

public class AlarmDrugAdapter extends RecyclerView.Adapter<AlarmDrugAdapter.ViewHolder> {
    List<Drugs> mDrugs = new ArrayList<>();
    AlarmDrugs mAlarm = new AlarmDrugs();
    private Escuchador escuchador;

    public AlarmDrugAdapter( Escuchador escuchador) {
        this.mDrugs = mDrugs;
        this.mAlarm = mAlarm;
        this.escuchador = escuchador;
    }

    public void setDrugs (List<Drugs> drugs){
        this.mDrugs = drugs;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AlarmDrugAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.card_view, parent,false);
        return new AlarmDrugAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmDrugAdapter.ViewHolder holder, int position) {

        Drugs drug = mDrugs.get(position);
        holder.txt1.setText(drug.getNombre());
        holder.txt2.setText(drug.getContraindicacciones());
        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                escuchador.DeleteAlarma(mAlarm);
                return false;
            }
        });


        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), drug.getNombre(),Toast.LENGTH_SHORT).show();
                mAlarm.setStarted(true);
                mAlarm.setCantidad(drug.getTotal());
                mAlarm.setAlarmDrugsId(drug.getId());
                mAlarm.setId_drugs(drug.getId());
                mAlarm.setTime(drug.getTiempo());
                mAlarm.schedule(v.getContext(),drug);
                escuchador.InsertAlarma(mAlarm);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDrugs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt1;
        TextView txt2;
        CardView card;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.cardview);
            txt1 = itemView.findViewById(R.id.text1);
            txt2 = itemView.findViewById(R.id.text2);
        }

    }
}
