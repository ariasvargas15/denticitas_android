package com.amongusdev.denticitas.cliente.perfil.interactor;

import android.util.Log;

import com.amongusdev.denticitas.cliente.perfil.interfaces.IPerfil;
import com.amongusdev.denticitas.model.apiservice.ApiAdapter;
import com.amongusdev.denticitas.model.apiservice.bodies.ClienteBody;
import com.amongusdev.denticitas.model.apiservice.bodies.GenericResponse;
import com.amongusdev.denticitas.model.apiservice.bodies.PersonaBody;
import com.amongusdev.denticitas.model.entities.Cliente;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilInteractor implements IPerfil.Interactor {

    IPerfil.Presenter presenter;

    public PerfilInteractor(IPerfil.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setDatosPersonales(String cedula, String nombre, String apellido, Date fechaNacimiento, String telefono, String direccion, String email, String ocupacion) {
        PersonaBody personaBody = new PersonaBody(nombre, apellido, fechaNacimiento, telefono, direccion, email, cedula, null);
        ClienteBody clienteBody = new ClienteBody(ocupacion, personaBody);
        Call<GenericResponse> call = ApiAdapter.getApiService().setDatosCliente(cedula, clienteBody);
        call.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                if (response.isSuccessful()) {
                    GenericResponse res = response.body();
                    if (res != null) {
                        boolean r;
                        r = res.getMessage().equals("success");
                        presenter.showResponse(r);
                    }
                } else {
                    Log.e("registro", response.message() + "\n" + response.toString());
                }
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
                Log.e("CompletarDatosError", call.toString());
            }
        });
    }

    @Override
    public void getCliente(String cedula) {
        Call<Cliente> call = ApiAdapter.getApiService().getCliente(cedula);
        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if (response.isSuccessful()) {
                    Cliente c = response.body();
                    if (c != null) {
                        presenter.setCliente(c);
                    }
                } else {
                    Log.e("getCliente", response.message() + "\n" + response.toString());
                }
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                Log.e("GetCliente", call.toString());
            }
        });
    }

}
