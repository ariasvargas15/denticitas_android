package com.amongusdev.denticitas.cliente.auth.interactor;

import android.content.Context;
import android.util.Log;

import com.amongusdev.denticitas.cliente.auth.interfaces.IRegistro;
import com.amongusdev.denticitas.model.apiservice.ApiAdapter;
import com.amongusdev.denticitas.model.apiservice.bodies.GenericResponse;
import com.amongusdev.denticitas.model.apiservice.bodies.LoginBody;
import com.amongusdev.denticitas.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroInteractor implements IRegistro.Interactor, Callback<GenericResponse> {

    IRegistro.Presenter presenter;
    Context context;
    String user;

    public RegistroInteractor(IRegistro.Presenter presenter, Context context){
       this.presenter = presenter;
       this.context = context;
    }

    @Override
    public void registrarse(String user, String password, String tipo) {
        this.user = user;
        LoginBody loginBody = new LoginBody(user, password, tipo);
        Call<GenericResponse> call = ApiAdapter.getApiService().registro(loginBody);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
        if (response.isSuccessful()) {
            GenericResponse res = response.body();
            if (res != null){
                boolean r;
                r = res.getMessage().equals("success");
                if (r){
                    Utils.saveValuePreference(context, "auth", user);
                }
                this.presenter.confirmarRegistro(r);
            }
        } else {
            Log.e("registro", response.message() + "\n" + response.toString());
        }
    }

    @Override
    public void onFailure(Call<GenericResponse> call, Throwable t) {
        Log.e("RegistroError", call.toString());
    }
}
