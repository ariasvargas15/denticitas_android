package com.amongusdev.denticitas.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Turno implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("hora_inicio")
    @Expose
    private Date horaInicio;
    @SerializedName("duracion")
    @Expose
    private Integer duracion;
    @SerializedName("estado")
    @Expose
    private Boolean estado;
    @SerializedName("diaAgenda")
    @Expose
    private DiaAgenda diaAgenda;
    @SerializedName("cita")
    @Expose
    private Cita cita;

    public Turno(Integer id, Date horaInicio, Integer duracion, Boolean estado) {
        this.id = id;
        this.horaInicio = horaInicio;
        this.duracion = duracion;
        this.estado = estado;
    }
}
