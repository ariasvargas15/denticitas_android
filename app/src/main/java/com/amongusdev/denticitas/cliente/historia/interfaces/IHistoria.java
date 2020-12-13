package com.amongusdev.denticitas.cliente.historia.interfaces;

import com.amongusdev.denticitas.model.entities.Evolucion;

import java.util.ArrayList;

public interface IHistoria {
    interface View {
        void setHistoria(ArrayList<Evolucion> historia);
    }
    interface Presenter{
        void getHistoria(String cedula);
        void setHistoria(ArrayList<Evolucion> historia);

    }
    interface Interactor{
        void getHistoria(String cedula);
    }
}
