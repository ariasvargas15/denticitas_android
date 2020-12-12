package com.amongusdev.denticitas.cliente.citas.interfaces;

import com.amongusdev.denticitas.model.entities.Cita;

import java.util.ArrayList;

public interface ICitas {
    interface View {
        void setCitas(ArrayList<Cita> citas);
        void showResponseDelete(boolean success);
    }
    interface Presenter{
        void getCitas(String cedula);
        void setCitas(ArrayList<Cita> citas);
        void deleteCita(Cita cita);
        void showResponseDelete(boolean success);
    }
    interface Interactor{
        void getCitas(String cedula);
        void deleteCita(Cita cita);
    }
}
