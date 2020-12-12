package com.amongusdev.denticitas.cliente.citas.view;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amongusdev.denticitas.R;
import com.amongusdev.denticitas.cliente.citas.adapter.CitaAdapter;
import com.amongusdev.denticitas.cliente.citas.interfaces.ICitas;
import com.amongusdev.denticitas.cliente.citas.interfaces.OnClickListenerCita;
import com.amongusdev.denticitas.cliente.citas.presenter.CitasPresenter;
import com.amongusdev.denticitas.model.entities.Cita;
import com.amongusdev.denticitas.utils.Utils;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;

public class CitasFragment extends Fragment implements ICitas.View, OnClickListenerCita {



    @BindView(R.id.recycler_citas)
    RecyclerView recycler;
    @BindView(R.id.citas)
    NestedScrollView citas;

    ICitas.Presenter presenter;
    AlertDialog dialog;
    String cedula;

    public CitasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_citas, container, false);
        ButterKnife.bind(this, v);
        presenter = new CitasPresenter(this);
        cedula = Utils.getValuePreference(getContext(), "auth");
        SpotsDialog.Builder sp = new SpotsDialog.Builder();
        sp.setContext(getContext()).setCancelable(false).setMessage("Loading...");
        dialog = sp.build();
        dialog.show();
        presenter.getCitas(cedula);
        return v;
    }


    @Override
    public void setCitas(ArrayList<Cita> citas) {
        CitaAdapter adapter = new CitaAdapter(citas, getContext());
        adapter.setOnClick(this);
        LinearLayoutManager lm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(lm);
        recycler.setAdapter(adapter);
        dialog.dismiss();
    }

    @Override
    public void showResponseDelete(boolean success) {
        dialog.dismiss();
        String msj = success ? "Cita eliminada correctamente" : "Error al eliminar la cita";
        Snackbar.make(citas, msj, Snackbar.LENGTH_SHORT).show();
        presenter.getCitas(cedula);
    }

    @Override
    public void deleteCita(Cita cita) {
        dialog.show();
        presenter.deleteCita(cita);
    }
}