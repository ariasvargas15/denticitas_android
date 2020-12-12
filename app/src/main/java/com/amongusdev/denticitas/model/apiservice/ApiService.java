package com.amongusdev.denticitas.model.apiservice;

import com.amongusdev.denticitas.model.apiservice.bodies.CitaBody;
import com.amongusdev.denticitas.model.apiservice.bodies.ClienteBody;
import com.amongusdev.denticitas.model.apiservice.bodies.GenericResponse;
import com.amongusdev.denticitas.model.apiservice.bodies.LoginBody;
import com.amongusdev.denticitas.model.entities.Agenda;
import com.amongusdev.denticitas.model.entities.Cita;
import com.amongusdev.denticitas.model.entities.Especialista;
import com.amongusdev.denticitas.model.entities.Servicio;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @GET("/servicio")
    Call<List<Servicio>> getServicios();

    @GET("/cita/cliente/{cedula}")
    Call<List<Cita>> getCitasCliente(@Path("cedula") String cedula);

    @GET("/area/{id}/especialista")
    Call<List<Especialista>> getEspecialistas(@Path("id") int id);

    @GET("/especialista/{cedula}/agenda")
    Call<List<Agenda>> getAgendaEspecialista(@Path("cedula") String cedula);

    @POST("/login")
    Call<GenericResponse> login(@Body LoginBody loginBody);

    @POST("/registro")
    Call<GenericResponse> registro(@Body LoginBody loginBody);

    @POST("/cita")
    Call<GenericResponse> createCita(@Body CitaBody citaBody);

    @PATCH("/cliente/{cedula}")
    Call<GenericResponse> setDatosCliente(@Path("cedula") String cedula, @Body ClienteBody clienteBody);

    @DELETE("/cita/{id}")
    Call<GenericResponse> deleteCita(@Path("id") int id);




}
