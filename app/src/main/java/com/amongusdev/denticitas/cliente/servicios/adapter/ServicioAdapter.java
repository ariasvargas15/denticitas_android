package com.amongusdev.denticitas.cliente.servicios.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amongusdev.denticitas.R;
import com.amongusdev.denticitas.model.entities.Servicio;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServicioAdapter extends RecyclerView.Adapter<ServicioAdapter.ServicioViewHolder> {

    private ArrayList<Servicio> servicios;

    public ServicioAdapter(ArrayList<Servicio> servicios) {
        this.servicios = servicios;
    }


    @NonNull
    @Override
    public ServicioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ServicioViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ServicioViewHolder holder, int position) {
        Servicio servicio = servicios.get(position);
        holder.nombreServicio.setText(servicio.getNombre());
        holder.descripcionServicio.setText(servicio.getDescripcion());
        holder.precio.setText("$ " + servicio.getPrecio());

    }

    @Override
    public int getItemCount() {
        return servicios.size();
    }

    static class ServicioViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.serviceName)
        TextView nombreServicio;
        @BindView(R.id.descripcionService)
        TextView descripcionServicio;
        @BindView(R.id.precio)
        TextView precio;
        @BindView(R.id.btn_agendar_servicio)
        MaterialButton agendar;

        public ServicioViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
