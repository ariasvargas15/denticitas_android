package com.amongusdev.denticitas.cliente.agendar.presenter;

import androidx.recyclerview.widget.ItemTouchUIUtil;

import com.amongusdev.denticitas.cliente.agendar.interactor.TurnoInteractor;
import com.amongusdev.denticitas.cliente.agendar.interfaces.ITurno;
import com.amongusdev.denticitas.model.entities.Turno;

import java.util.ArrayList;

public class TurnoPresenter implements ITurno.Presenter {

    ITurno.Interactor interactor;
    ITurno.View view;

    public TurnoPresenter(ITurno.View view){
        this.view = view;
        interactor = new TurnoInteractor(this);
    }

    @Override
    public void setTurnos(ArrayList<Turno> turnos) {
        view.setTurnos(turnos);
    }

    @Override
    public void getTurnos() {
        interactor.getTurnos();
    }
}
