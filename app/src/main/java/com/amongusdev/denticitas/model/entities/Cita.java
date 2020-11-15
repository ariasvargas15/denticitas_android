package com.amongusdev.denticitas.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cita implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("create_time")
    @Expose
    private Date createTime;
    @SerializedName("cliente")
    @Expose
    private Cliente cliente;
    @SerializedName("turno")
    @Expose
    private Turno turno;
    @SerializedName("servicio")
    @Expose
    private Servicio servicio;

}