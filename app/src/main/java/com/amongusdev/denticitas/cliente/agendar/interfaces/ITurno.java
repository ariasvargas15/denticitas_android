package com.amongusdev.denticitas.cliente.agendar.interfaces;

import com.amongusdev.denticitas.model.entities.Turno;

import java.util.ArrayList;

public interface ITurno {
    interface View {
        void setTurnos(ArrayList<Turno> turnos);
    }
    interface Presenter{
        void setTurnos(ArrayList<Turno> turnos);
        void getTurnos();
    }
    interface Interactor{
        void getTurnos();
    }
}
