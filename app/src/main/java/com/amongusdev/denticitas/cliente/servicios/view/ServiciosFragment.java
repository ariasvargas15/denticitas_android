package com.amongusdev.denticitas.cliente.servicios.view;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amongusdev.denticitas.R;
import com.amongusdev.denticitas.cliente.auth.view.LoginActivity;
import com.amongusdev.denticitas.cliente.servicios.adapter.ServicioAdapter;
import com.amongusdev.denticitas.cliente.servicios.interfaces.IServicios;
import com.amongusdev.denticitas.cliente.servicios.interfaces.OnClickListenerServicio;
import com.amongusdev.denticitas.cliente.servicios.presenter.ServiciosPresenter;
import com.amongusdev.denticitas.model.entities.Servicio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;

public class ServiciosFragment extends Fragment implements IServicios.View, OnClickListenerServicio {


    @BindView(R.id.recycler_servicios)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_servicios)
    SwipeRefreshLayout mSwipeRefreshLayout;

    NavController navController;
    ServicioAdapter adapter;
    private AlertDialog dialog;

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
        SpotsDialog.Builder sp = new SpotsDialog.Builder();
        sp.setContext(getContext()).setCancelable(false).setMessage("Loading...");
        dialog = sp.build();
        dialog.show();
        presenter.buscarServicios();
        setSwipe();

        return v;
    }

    private void setSwipe() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.buscarServicios();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
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
        adapter = new ServicioAdapter(servicios);
        adapter.setOnClick(this);
        recyclerView.setAdapter(adapter);
        dialog.dismiss();
    }

    @Override
    public void onClickAgendarServicio(Servicio servicio) {
        Bundle b = new Bundle();
        b.putSerializable("servicio", servicio);
        navController.navigate(R.id.action_nav_servicios_to_nav_agendar, b);
    }
}

