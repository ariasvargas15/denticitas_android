package com.amongusdev.denticitas.cliente.agendar.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amongusdev.denticitas.R;
import com.amongusdev.denticitas.cliente.servicios.adapter.ServicioAdapter;
import com.amongusdev.denticitas.model.entities.Turno;
import com.amongusdev.denticitas.utils.Utils;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TurnoAdapter extends RecyclerView.Adapter<TurnoAdapter.TurnoViewHolder> {

    ArrayList<Turno> turnos;
    Context context;

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

        holder.hora.setText(turno.getHoraInicio());

        String text = turno.getEstado() ? "Agendar" : "Ocupado";
        int color = turno.getEstado() ?  R.color.colorPrimary : R.color.DarkGray;

        holder.estado.setText(text);
        holder.estado.setBackgroundColor(context.getResources().getColor(color, context.getTheme()));
        holder.estado.setEnabled(turno.getEstado());

        holder.estado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.estado.setEnabled(false);
                holder.estado.setText("Cita Agendada :)");
                holder.estado.setBackgroundColor(context.getResources().getColor(R.color.Green, context.getTheme()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return turnos.size();
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
