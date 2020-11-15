package com.amongusdev.denticitas.cliente.agendar.interactor;

import com.amongusdev.denticitas.cliente.agendar.interfaces.ITurno;
import com.amongusdev.denticitas.model.entities.Turno;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TurnoInteractor implements ITurno.Interactor {

    ITurno.Presenter presenter;
    ArrayList<Turno> turnos;

    public TurnoInteractor(ITurno.Presenter presenter){
        this.presenter = presenter;
        turnos = new ArrayList<>();
    }

    @Override
    public void getTurnos() {
       /* turnos.add(new Turno(1, new Date(2020, 11, 11, 8, 0), 30, true));
        turnos.add(new Turno(1, new Date(2020, 11, 11, 8, 30), 30, false));
        turnos.add(new Turno(1, new Date(2020, 11, 11, 9, 0), 30, true));
        turnos.add(new Turno(1, new Date(2020, 11, 11, 9, 30), 30, false));
        turnos.add(new Turno(1, new Date(2020, 11, 11, 10, 0), 30, true));
        turnos.add(new Turno(1, new Date(2020, 11, 11, 10, 30), 30, false));
        turnos.add(new Turno(1, new Date(2020, 11, 11, 11, 0), 30, true));
        turnos.add(new Turno(1, new Date(2020, 11, 11, 11, 30), 30, true));
        turnos.add(new Turno(1, new Date(2020, 11, 11, 12, 0), 30, true));
        turnos.add(new Turno(1, new Date(2020, 11, 11, 12, 30), 30, false));
        turnos.add(new Turno(1, new Date(2020, 11, 11, 13, 0), 30, false));
        turnos.add(new Turno(1, new Date(2020, 11, 11, 13, 30), 30, false));
        turnos.add(new Turno(1, new Date(2020, 11, 11, 14, 0), 30, false));
        turnos.add(new Turno(1, new Date(2020, 11, 11, 14, 30), 30, false));
        turnos.add(new Turno(1, new Date(2020, 11, 11, 15, 0), 30, false));
        turnos.add(new Turno(1, new Date(2020, 11, 11, 15, 30), 30, false));
        turnos.add(new Turno(1, new Date(2020, 11, 11, 16, 0), 30, false));
        turnos.add(new Turno(1, new Date(2020, 11, 11, 16, 30), 30, false));
        turnos.add(new Turno(1, new Date(2020, 11, 11, 17, 0), 30, false));
        turnos.add(new Turno(1, new Date(2020, 11, 11, 17, 30), 30, false));
*/
        presenter.setTurnos(turnos);
    }
}
