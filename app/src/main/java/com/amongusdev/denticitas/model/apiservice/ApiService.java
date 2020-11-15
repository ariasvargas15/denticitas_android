package com.amongusdev.denticitas.model.apiservice;

import com.amongusdev.denticitas.model.entities.Cliente;
import com.amongusdev.denticitas.model.entities.GenericResponse;
import com.amongusdev.denticitas.model.entities.LoginBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @GET("cliente/{cedula}")
    Call<Cliente> getCliente(@Path("cedula") String cedula);

    @POST("/login")
    Call<GenericResponse> login(@Body LoginBody loginBody);
}
