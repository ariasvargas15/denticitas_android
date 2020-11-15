package com.amongusdev.denticitas.model.apiservice;

import com.amongusdev.denticitas.model.apiservice.bodies.ClienteBody;
import com.amongusdev.denticitas.model.apiservice.bodies.GenericResponse;
import com.amongusdev.denticitas.model.apiservice.bodies.LoginBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {


    @POST("/login")
    Call<GenericResponse> login(@Body LoginBody loginBody);

    @POST("/registro")
    Call<GenericResponse> registro(@Body LoginBody loginBody);

    @PATCH("/cliente/{cedula}")
    Call<GenericResponse> setDatosCliente(@Path("cedula") String cedula, @Body ClienteBody clienteBody);
}
