package com.amongusdev.denticitas.cliente.citas.interactor;

import com.amongusdev.denticitas.cliente.citas.interfaces.ICitas;
import com.amongusdev.denticitas.model.entities.Cita;

import java.util.ArrayList;

public class CitasInteractor implements ICitas.Interactor {

    ICitas.Presenter presenter;
    ArrayList<Cita> citas;

    public CitasInteractor(ICitas.Presenter presenter){
        this.presenter = presenter;
        citas = new ArrayList<>();
    }

    @Override
    public void getCitas() {
        citas.add(new Cita());
        citas.add(new Cita());
        citas.add(new Cita());
        citas.add(new Cita());
        citas.add(new Cita());
        citas.add(new Cita());
        citas.add(new Cita());

        presenter.setCitas(citas);
    }
}
