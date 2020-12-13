package com.amongusdev.denticitas.cliente.historia.view;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.support.v4.media.session.ParcelableVolumeInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amongusdev.denticitas.R;
import com.amongusdev.denticitas.cliente.citas.adapter.CitaAdapter;
import com.amongusdev.denticitas.cliente.citas.interfaces.ICitas;
import com.amongusdev.denticitas.cliente.citas.presenter.CitasPresenter;
import com.amongusdev.denticitas.cliente.historia.adapter.EvolucionAdapter;
import com.amongusdev.denticitas.cliente.historia.interfaces.IHistoria;
import com.amongusdev.denticitas.cliente.historia.presenter.HistoriaPresenter;
import com.amongusdev.denticitas.model.entities.Cita;
import com.amongusdev.denticitas.model.entities.Evolucion;
import com.amongusdev.denticitas.utils.Utils;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;

public class HistoriaFragment extends Fragment implements IHistoria.View {


    @BindView(R.id.recycler_historia)
    RecyclerView recycler;
    @BindView(R.id.historia)
    NestedScrollView historia;

    IHistoria.Presenter presenter;
    AlertDialog dialog;
    String cedula;
    EvolucionAdapter adapter;
    ArrayList<Evolucion> evolucion;

    public HistoriaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_historia, container, false);
        ButterKnife.bind(this, v);
        presenter = new HistoriaPresenter(this);
        cedula = Utils.getValuePreference(getContext(), "auth");
        SpotsDialog.Builder sp = new SpotsDialog.Builder();
        sp.setContext(getContext()).setCancelable(false).setMessage("Loading...");
        dialog = sp.build();
        dialog.show();
        presenter.getHistoria(cedula);
        return v;
    }


    @Override
    public void setHistoria(ArrayList<Evolucion> citas) {
        evolucion = citas;
        adapter = new EvolucionAdapter(evolucion, getContext());
        LinearLayoutManager lm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(lm);
        recycler.setAdapter(adapter);
        dialog.dismiss();
    }
}