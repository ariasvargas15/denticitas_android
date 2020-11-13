package com.amongusdev.denticitas.cliente.citas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amongusdev.denticitas.R;
import com.amongusdev.denticitas.cliente.agendar.adapter.TurnoAdapter;
import com.amongusdev.denticitas.model.entities.Cita;
import com.amongusdev.denticitas.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CitaAdapter extends RecyclerView.Adapter<CitaAdapter.CitaViewHolder> {

    ArrayList<Cita> citas;
    Context context;

    public CitaAdapter(ArrayList<Cita> citas, Context context){
        this.citas = citas;
        this.context = context;
    }

    @NonNull
    @Override
    public CitaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CitaViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cita, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CitaViewHolder holder, int position) {
        Cita cita = citas.get(position);
        holder.hora.setText("11:00 AM");
        holder.fecha.setText(Utils.dateToString(getCalendar()));
        holder.especialista.setText("Esp. Diana Romero");
        holder.servicio.setText("Ortodoncia");

        boolean res = citaPasada();
        int drawable = res ? R.drawable.shape_card_cita : R.drawable.shape_card_cita_past;
        int color = res ? R.color.colorPrimary : R.color.White;

        holder.linear.setBackgroundResource(drawable);
        holder.hora.setTextColor(context.getResources().getColor(color, context.getTheme()));
        holder.servicio.setTextColor(context.getResources().getColor(color, context.getTheme()));
        holder.especialista.setTextColor(context.getResources().getColor(color, context.getTheme()));
    }

    private Calendar getCalendar(){
        //TODO
        return Calendar.getInstance();
    }

    private boolean citaPasada() {
        //TODO
        return new Random().nextBoolean();
    }

    @Override
    public int getItemCount() {
        return citas.size();
    }

    class CitaViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.fecha_cita)
        TextView fecha;
        @BindView(R.id.servicio_cita)
        TextView servicio;
        @BindView(R.id.hora_cita)
        TextView hora;
        @BindView(R.id.especialista_cita)
        TextView especialista;
        @BindView(R.id.linear_cita)
        LinearLayout linear;

        public CitaViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
