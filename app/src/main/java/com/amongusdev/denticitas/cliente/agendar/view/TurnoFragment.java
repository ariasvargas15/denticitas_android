package com.amongusdev.denticitas.cliente.agendar.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amongusdev.denticitas.R;
import com.amongusdev.denticitas.cliente.agendar.adapter.TurnoAdapter;
import com.amongusdev.denticitas.cliente.agendar.interfaces.ITurno;
import com.amongusdev.denticitas.cliente.agendar.interfaces.OnClickListenerCita;
import com.amongusdev.denticitas.cliente.agendar.presenter.TurnoPresenter;
import com.amongusdev.denticitas.cliente.servicios.adapter.ServicioAdapter;
import com.amongusdev.denticitas.model.entities.DiaAgenda;
import com.amongusdev.denticitas.model.entities.Servicio;
import com.amongusdev.denticitas.model.entities.Turno;
import com.amongusdev.denticitas.utils.Utils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TurnoFragment extends Fragment implements ITurno.View, OnClickListenerCita {

    @BindView(R.id.recycler_turnos)
    RecyclerView recycler;
    @BindView(R.id.confirmar_turno)
    TextView confirmar;
    @BindView(R.id.fecha_turno)
    TextView fecha;
    @BindView(R.id.turnos)
    LinearLayout turnos;

    ITurno.Presenter presenter;
    private static final String ARG_PARAM1 = "agenda";
    private static final String ARG_PARAM2 = "servicio";

    private DiaAgenda diaAgenda;
    private Servicio servicio;

    public TurnoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            diaAgenda = (DiaAgenda) getArguments().getSerializable(ARG_PARAM1);
            servicio = (Servicio) getArguments().getSerializable(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_turno, container, false);
        ButterKnife.bind(this, v);
        presenter = new TurnoPresenter(this);


        setAdapter();
        return v;
    }

    private void setAdapter(){
        TurnoAdapter adapter = new TurnoAdapter((ArrayList<Turno>) diaAgenda.getTurnos(), getActivity());
        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        adapter.setOnClick(this);
        recycler.setLayoutManager(lm);
        recycler.setAdapter(adapter);
    }

    @Override
    public void success(boolean success) {
        String msj = success ? "Cita creada correctamente" : "Error al crear Cita";
        Snackbar.make(turnos, msj, Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void crearCita(int idTurno) {
        String ced = Utils.getValuePreference(getContext(), "auth");
        presenter.createCita(ced, servicio.getId(), idTurno);
    }
}