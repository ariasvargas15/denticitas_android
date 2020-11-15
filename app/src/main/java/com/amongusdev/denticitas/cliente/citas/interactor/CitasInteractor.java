package com.amongusdev.denticitas.cliente.citas.interactor;

import android.util.Log;

import com.amongusdev.denticitas.cliente.citas.interfaces.ICitas;
import com.amongusdev.denticitas.model.apiservice.ApiAdapter;
import com.amongusdev.denticitas.model.entities.Cita;
import com.amongusdev.denticitas.model.entities.Servicio;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CitasInteractor implements ICitas.Interactor, Callback<List<Cita>> {

    ICitas.Presenter presenter;
    ArrayList<Cita> citas;

    public CitasInteractor(ICitas.Presenter presenter){
        this.presenter = presenter;
        citas = new ArrayList<>();
    }

    @Override
    public void getCitas(String cedula) {
        Call<List<Cita>> call = ApiAdapter.getApiService().getCitasCliente(cedula);
        call.enqueue(this);
        presenter.setCitas(citas);
    }

    @Override
    public void onResponse(Call<List<Cita>> call, Response<List<Cita>> response) {
        if (response.isSuccessful()) {
            citas = (ArrayList<Cita>)response.body();
            if(citas != null) {
                presenter.setCitas(citas);
            } else {
                Log.e("onResponseCita", "Response is null");
            }
        }
    }

    @Override
    public void onFailure(Call<List<Cita>> call, Throwable t) {
        Log.e("getCitasFailed", "Response failed");
    }
}
