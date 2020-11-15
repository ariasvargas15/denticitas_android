package com.amongusdev.denticitas.cliente.servicios.interactor;

import android.util.Log;

import com.amongusdev.denticitas.cliente.servicios.interfaces.IServicios;
import com.amongusdev.denticitas.model.apiservice.ApiAdapter;
import com.amongusdev.denticitas.model.entities.Servicio;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiciosInteractor implements IServicios.Interactor, Callback<List<Servicio>> {

    private ArrayList<Servicio> servicios;
    private IServicios.Presenter presenter;

    public ServiciosInteractor(IServicios.Presenter presenter){
        this.presenter = presenter;
        servicios = new ArrayList<>();
    }

    @Override
    public void buscarServicios() {
        Call<List<Servicio>> call = ApiAdapter.getApiService().getServicios();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Servicio>> call, Response<List<Servicio>> response) {
        if (response.isSuccessful()) {
            servicios = (ArrayList<Servicio>)response.body();
            if(servicios != null) {
                presenter.mostrarServicios(servicios);
            } else {
                Log.e("onResponseServicios", "Response is null");
            }
        }
    }

    @Override
    public void onFailure(Call<List<Servicio>> call, Throwable t) {
        Log.e("getServicesFailed", "Response failed");

    }
}
