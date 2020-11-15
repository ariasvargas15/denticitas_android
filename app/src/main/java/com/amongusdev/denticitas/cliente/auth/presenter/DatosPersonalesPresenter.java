package com.amongusdev.denticitas.cliente.auth.presenter;

import com.amongusdev.denticitas.cliente.auth.interactor.DatosPersonalesInteractor;
import com.amongusdev.denticitas.cliente.auth.interfaces.IDatosPersonales;

import java.util.Date;

public class DatosPersonalesPresenter implements IDatosPersonales.Presenter {

    IDatosPersonales.View view;
    IDatosPersonales.Interactor interactor;

    public DatosPersonalesPresenter(IDatosPersonales.View view) {
        this.view = view;
        this.interactor = new DatosPersonalesInteractor(this);
    }

    @Override
    public void setDatosPersonales(String cedula, String nombre, String apellido, Date fechaNacimiento, String telefono, String direccion, String email, String ocupacion) {
        interactor.setDatosPersonales(cedula, nombre, apellido, fechaNacimiento, telefono, direccion, email, ocupacion);
    }

    @Override
    public void showResponse(boolean success) {
        view.showResponse(success);
    }
}
