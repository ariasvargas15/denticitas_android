package com.amongusdev.denticitas.model.apiservice;

import com.amongusdev.denticitas.model.apiservice.bodies.ClienteBody;
import com.amongusdev.denticitas.model.apiservice.bodies.GenericResponse;
import com.amongusdev.denticitas.model.apiservice.bodies.LoginBody;
import com.amongusdev.denticitas.model.entities.Servicio;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {


    @POST("/login")
    Call<GenericResponse> login(@Body LoginBody loginBody);

    @POST("/registro")
    Call<GenericResponse> registro(@Body LoginBody loginBody);

    @PATCH("/cliente/{cedula}")
    Call<GenericResponse> setDatosCliente(@Path("cedula") String cedula, @Body ClienteBody clienteBody);

    @GET("/servicio")
    Call<List<Servicio>> getServicios();
}
