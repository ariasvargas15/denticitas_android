package com.amongusdev.denticitas.cliente.agendar.interactor;

import android.util.Log;

import com.amongusdev.denticitas.cliente.agendar.interfaces.ITurno;
import com.amongusdev.denticitas.model.apiservice.ApiAdapter;
import com.amongusdev.denticitas.model.apiservice.bodies.CitaBody;
import com.amongusdev.denticitas.model.apiservice.bodies.GenericResponse;
import com.amongusdev.denticitas.model.entities.Turno;
import com.amongusdev.denticitas.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TurnoInteractor implements ITurno.Interactor, Callback<GenericResponse> {

    ITurno.Presenter presenter;
    ArrayList<Turno> turnos;

    public TurnoInteractor(ITurno.Presenter presenter){
        this.presenter = presenter;
        turnos = new ArrayList<>();
    }

    @Override
    public void createCita(String cedula, int servicio, int turno) {
        CitaBody citaBody = new CitaBody(cedula, servicio, turno, null);
        Call<GenericResponse> call = ApiAdapter.getApiService().createCita(citaBody);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
        if (response.isSuccessful()) {
            GenericResponse res = response.body();
            if (res != null){
                boolean r;
                r = res.getMessage().equals("success");
                this.presenter.success(r);
            }
        } else {
            Log.e("cita", response.message() + "\n" + response.toString());
        }
    }

    @Override
    public void onFailure(Call<GenericResponse> call, Throwable t) {
        Log.e("CitaError", call.toString());

    }
}
