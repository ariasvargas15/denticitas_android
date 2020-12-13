package com.amongusdev.denticitas.cliente.historia.presenter;

import com.amongusdev.denticitas.cliente.historia.interactor.HistoriaInteractor;
import com.amongusdev.denticitas.cliente.historia.interfaces.IHistoria;
import com.amongusdev.denticitas.model.entities.Evolucion;

import java.util.ArrayList;

public class HistoriaPresenter implements IHistoria.Presenter {

    IHistoria.View view;
    IHistoria.Interactor interactor;

    public HistoriaPresenter(IHistoria.View view) {
        this.view = view;
        this.interactor = new HistoriaInteractor(this);
    }

    @Override
    public void getHistoria(String cedula) {
        interactor.getHistoria(cedula);
    }

    @Override
    public void setHistoria(ArrayList<Evolucion> historia) {
        view.setHistoria(historia);
    }
}
