package com.amongusdev.denticitas.cliente.citas.interactor;

import android.util.Log;

import com.amongusdev.denticitas.cliente.citas.interfaces.ICitas;
import com.amongusdev.denticitas.model.apiservice.ApiAdapter;
import com.amongusdev.denticitas.model.apiservice.bodies.GenericResponse;
import com.amongusdev.denticitas.model.entities.Cita;
import com.amongusdev.denticitas.model.entities.Servicio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CitasInteractor implements ICitas.Interactor {

    ICitas.Presenter presenter;
    ArrayList<Cita> citas;

    public CitasInteractor(ICitas.Presenter presenter){
        this.presenter = presenter;
        citas = new ArrayList<>();
    }

    @Override
    public void getCitas(String cedula) {
        Call<List<Cita>> call = ApiAdapter.getApiService().getCitasCliente(cedula);
        call.enqueue(new Callback<List<Cita>>() {
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
        });
        presenter.setCitas(citas);
    }

    @Override
    public void deleteCita(Cita cita) {
        Call<GenericResponse> call = ApiAdapter.getApiService().deleteCita(cita.getId());
        call.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                if (response.isSuccessful()) {
                    GenericResponse res = response.body();
                    if (res != null) {
                        boolean r;
                        r = res.getMessage().equals("success");
                        presenter.showResponseDelete(r);
                    }
                } else {
                    Log.e("deleteCita", response.message() + "\n" + response.toString());
                }
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
                Log.e("deleteCitasFailed", "Response failed");
            }
        });
    }


}
