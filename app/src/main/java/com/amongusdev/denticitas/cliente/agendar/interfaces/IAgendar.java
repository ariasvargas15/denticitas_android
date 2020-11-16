package com.amongusdev.denticitas.cliente.agendar.interfaces;

import com.amongusdev.denticitas.model.entities.Agenda;
import com.amongusdev.denticitas.model.entities.Especialista;

import java.util.ArrayList;

public interface IAgendar {
    interface View {

        void setEspecialistas(ArrayList<Especialista> especialistas);

        void setAgendas(ArrayList<Agenda> agendas);

    }

    interface Presenter {

        void getEspecialistas(int area);

        void setEspecialistas(ArrayList<Especialista> especialistas);

        void getAgendas(String cedula);

        void setAgendas(ArrayList<Agenda> agendas);

    }

    interface Interactor {

        void getEspecialistas(int area);

        void getAgendas(String cedula);

    }
}
