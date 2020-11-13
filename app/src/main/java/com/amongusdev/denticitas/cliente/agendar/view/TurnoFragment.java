package com.amongusdev.denticitas.cliente.agendar.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amongusdev.denticitas.R;
import com.amongusdev.denticitas.cliente.agendar.adapter.TurnoAdapter;
import com.amongusdev.denticitas.cliente.agendar.interfaces.ITurno;
import com.amongusdev.denticitas.cliente.agendar.presenter.TurnoPresenter;
import com.amongusdev.denticitas.cliente.servicios.adapter.ServicioAdapter;
import com.amongusdev.denticitas.model.entities.Turno;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TurnoFragment extends Fragment implements ITurno.View {

    @BindView(R.id.recycler_turnos)
    RecyclerView recycler;
    @BindView(R.id.confirmar_turno)
    TextView confirmar;
    @BindView(R.id.fecha_turno)
    TextView fecha;

    ITurno.Presenter presenter;
    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    public TurnoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_turno, container, false);
        ButterKnife.bind(this, v);
        presenter = new TurnoPresenter(this);
        presenter.getTurnos();
        return v;
    }


    @Override
    public void setTurnos(ArrayList<Turno> turnos) {
        TurnoAdapter adapter = new TurnoAdapter(turnos, getActivity());
        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(lm);
        recycler.setAdapter(adapter);
    }
}