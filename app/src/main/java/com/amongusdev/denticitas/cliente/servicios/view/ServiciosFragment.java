package com.amongusdev.denticitas.cliente.servicios.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amongusdev.denticitas.R;
import com.amongusdev.denticitas.cliente.servicios.adapter.ServicioAdapter;
import com.amongusdev.denticitas.cliente.servicios.interfaces.IServicios;
import com.amongusdev.denticitas.cliente.servicios.interfaces.OnClickListenerServicio;
import com.amongusdev.denticitas.cliente.servicios.presenter.ServiciosPresenter;
import com.amongusdev.denticitas.model.entities.Servicio;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServiciosFragment extends Fragment implements IServicios.View, OnClickListenerServicio {


    @BindView(R.id.recycler_servicios)
    RecyclerView recyclerView;

    NavController navController;

    private IServicios.Presenter presenter;

    public ServiciosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_servicios, container, false);
        ButterKnife.bind(this, v);

        presenter = new ServiciosPresenter(this);
        presenter.buscarServicios();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    @Override
    public void mostrarServicios(ArrayList<Servicio> servicios) {
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lm);
        ServicioAdapter adapter = new ServicioAdapter(servicios);
        adapter.setOnClick(this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onClickAgendarServicio(Servicio servicio) {
        navController.navigate(R.id.action_nav_servicios_to_nav_agendar);
    }
}