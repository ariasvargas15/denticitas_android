package com.amongusdev.denticitas.cliente.agendar.presenter;

import com.amongusdev.denticitas.cliente.agendar.interactor.AgendarInteractor;
import com.amongusdev.denticitas.cliente.agendar.interfaces.IAgendar;
import com.amongusdev.denticitas.model.entities.Agenda;
import com.amongusdev.denticitas.model.entities.Especialista;

import java.util.ArrayList;

public class AgendarPresenter implements IAgendar.Presenter {

    IAgendar.View view;
    IAgendar.Interactor interactor;

    public AgendarPresenter(IAgendar.View view) {
        this.view = view;
        this.interactor = new AgendarInteractor(this);
    }

    @Override
    public void getEspecialistas(int area) {
        interactor.getEspecialistas(area);
    }

    @Override
    public void setEspecialistas(ArrayList<Especialista> especialistas) {
        view.setEspecialistas(especialistas);
    }

    @Override
    public void getAgendas(String cedula) {
        interactor.getAgendas(cedula);
    }

    @Override
    public void setAgendas(ArrayList<Agenda> agendas) {
        view.setAgendas(agendas);
    }
}
