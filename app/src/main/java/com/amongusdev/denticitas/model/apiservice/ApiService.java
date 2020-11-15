package com.amongusdev.denticitas.model.apiservice;

import com.amongusdev.denticitas.model.entities.GenericResponse;
import com.amongusdev.denticitas.model.entities.LoginBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {


    @POST("/login")
    Call<GenericResponse> login(@Body LoginBody loginBody);

    @POST("/registro")
    Call<GenericResponse> registro(@Body LoginBody loginBody);
}
