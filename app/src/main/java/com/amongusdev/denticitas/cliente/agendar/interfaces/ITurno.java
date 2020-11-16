package com.amongusdev.denticitas.cliente.agendar.interfaces;

import com.amongusdev.denticitas.model.entities.Turno;

import java.util.ArrayList;

public interface ITurno {
    interface View {
        void success(boolean success);
    }
    interface Presenter{
        void success(boolean success);
        void createCita(String cedula, int servicio, int turno);
    }
    interface Interactor{
        void createCita(String cedula, int servicio, int turno);
    }
}
