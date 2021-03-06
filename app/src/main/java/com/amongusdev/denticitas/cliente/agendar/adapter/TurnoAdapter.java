package com.amongusdev.denticitas.cliente.agendar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amongusdev.denticitas.R;
import com.amongusdev.denticitas.cliente.agendar.interfaces.OnClickListenerTurno;
import com.amongusdev.denticitas.model.entities.Turno;
import com.amongusdev.denticitas.utils.Utils;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TurnoAdapter extends RecyclerView.Adapter<TurnoAdapter.TurnoViewHolder> {

    ArrayList<Turno> turnos;
    Context context;
    OnClickListenerTurno onClick;

    public TurnoAdapter(ArrayList<Turno> turnos, Context context) {
        this.turnos = turnos;
        this.context = context;
    }

    @NonNull
    @Override
    public TurnoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TurnoAdapter.TurnoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_turno, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TurnoViewHolder holder, int position) {
        Turno turno = turnos.get(position);

        String h = Utils.convert24HourToAmPm(turno.getHoraInicio());
        holder.hora.setText(h);

        String text = turno.getDisponible() ? "Agendar" : "Ocupado";
        int color = turno.getDisponible() ?  R.color.colorPrimary : R.color.DarkGray;

        holder.estado.setText(text);
        holder.estado.setBackgroundColor(context.getResources().getColor(color, context.getTheme()));
        holder.estado.setEnabled(turno.getDisponible());

        holder.estado.setOnClickListener(v -> {
            onClick.crearCita(turno.getId());
            holder.estado.setEnabled(false);
            holder.estado.setText("Cita Agendada :)");
            holder.estado.setBackgroundColor(context.getResources().getColor(R.color.Green, context.getTheme()));
        });

    }

    @Override
    public int getItemCount() {
        return turnos.size();
    }

    public void setOnClick(OnClickListenerTurno onClick){
        this.onClick = onClick;
    }

    class TurnoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.estado_turno)
        MaterialButton estado;
        @BindView(R.id.hora_turno)
        TextView hora;

        public TurnoViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
