package com.amongusdev.denticitas.cliente.auth.interactor;

import android.util.Log;

import com.amongusdev.denticitas.cliente.auth.interfaces.IDatosPersonales;
import com.amongusdev.denticitas.model.apiservice.ApiAdapter;
import com.amongusdev.denticitas.model.apiservice.bodies.ClienteBody;
import com.amongusdev.denticitas.model.apiservice.bodies.GenericResponse;
import com.amongusdev.denticitas.model.apiservice.bodies.PersonaBody;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatosPersonalesInteractor implements IDatosPersonales.Interactor, Callback<GenericResponse> {

    IDatosPersonales.Presenter presenter;

    public DatosPersonalesInteractor(IDatosPersonales.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setDatosPersonales(String cedula, String nombre, String apellido, Date fechaNacimiento, String telefono, String direccion, String email, String ocupacion) {
        PersonaBody personaBody = new PersonaBody(nombre, apellido, fechaNacimiento, telefono, direccion, email, cedula, null);
        ClienteBody clienteBody = new ClienteBody(ocupacion, personaBody);

        Call<GenericResponse> call = ApiAdapter.getApiService().setDatosCliente(cedula, clienteBody);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
        if (response.isSuccessful()) {
            GenericResponse res = response.body();
            if (res != null) {
                boolean r;
                r = res.getMessage().equals("success");
                this.presenter.showResponse(r);
            }
        } else {
            Log.e("registro", response.message() + "\n" + response.toString());
        }
    }

    @Override
    public void onFailure(Call<GenericResponse> call, Throwable t) {
        Log.e("CompletarDatosError", call.toString());
    }
}
