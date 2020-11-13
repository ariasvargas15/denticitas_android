package com.amongusdev.denticitas.cliente.citas.interfaces;

import com.amongusdev.denticitas.model.entities.Cita;

import java.util.ArrayList;

public interface ICitas {
    interface View {
        void setCitas(ArrayList<Cita> citas);
    }
    interface Presenter{
        void getCitas();
        void setCitas(ArrayList<Cita> citas);
    }
    interface Interactor{
        void getCitas();
    }
}
