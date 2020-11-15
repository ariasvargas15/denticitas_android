package com.amongusdev.denticitas.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginBody {
    @SerializedName("cedula")
    @Expose
    private String cedula;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("tipo")
    @Expose
    private String tipo;

}
