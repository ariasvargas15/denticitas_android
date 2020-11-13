package com.amongusdev.denticitas.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Agenda implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("mes")
    @Expose
    private Integer mes;
    @SerializedName("anio")
    @Expose
    private Integer anio;
    @SerializedName("agendaId")
    @Expose
    private List<DiaAgenda> diaAgendaList;
    @SerializedName("especialista_cedula")
    @Expose
    private Especialista especialistaCedula;
}
