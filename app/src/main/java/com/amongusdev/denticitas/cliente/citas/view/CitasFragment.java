package com.amongusdev.denticitas.cliente.citas.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amongusdev.denticitas.R;
import com.amongusdev.denticitas.cliente.citas.adapter.CitaAdapter;
import com.amongusdev.denticitas.cliente.citas.interfaces.ICitas;
import com.amongusdev.denticitas.cliente.citas.presenter.CitasPresenter;
import com.amongusdev.denticitas.model.entities.Cita;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CitasFragment extends Fragment implements ICitas.View {

    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    @BindView(R.id.recycler_citas)
    RecyclerView recycler;

    ICitas.Presenter presenter;

    public CitasFragment() {
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
        View v = inflater.inflate(R.layout.fragment_citas, container, false);
        ButterKnife.bind(this, v);
        presenter = new CitasPresenter(this);
        presenter.getCitas();
        return v;
    }


    @Override
    public void setCitas(ArrayList<Cita> citas) {
        CitaAdapter adapter = new CitaAdapter(citas, getContext());
        LinearLayoutManager lm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(lm);
        recycler.setAdapter(adapter);
    }
}