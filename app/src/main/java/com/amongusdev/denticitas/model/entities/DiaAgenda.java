package com.amongusdev.denticitas.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DiaAgenda implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("dia")
    @Expose
    private Integer dia;
    @SerializedName("agenda")
    @Expose
    private Agenda agendaId;
    @SerializedName("turnoList")
    @Expose
    private List<Turno> turnoList;
}
