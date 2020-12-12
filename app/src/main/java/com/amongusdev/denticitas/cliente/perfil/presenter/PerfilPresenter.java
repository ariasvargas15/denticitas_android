package com.amongusdev.denticitas.cliente.perfil.presenter;

import com.amongusdev.denticitas.cliente.perfil.interactor.PerfilInteractor;
import com.amongusdev.denticitas.cliente.perfil.interfaces.IPerfil;
import com.amongusdev.denticitas.model.entities.Cliente;

import java.util.Date;

public class PerfilPresenter implements IPerfil.Presenter {

    IPerfil.View view;
    IPerfil.Interactor interactor;

    public PerfilPresenter(IPerfil.View view) {
        this.view = view;
        this.interactor = new PerfilInteractor(this);
    }

    @Override
    public void setDatosPersonales(String cedula, String nombre, String apellido, Date fechaNacimiento, String telefono, String direccion, String email, String ocupacion) {
        interactor.setDatosPersonales(cedula, nombre, apellido, fechaNacimiento, telefono, direccion, email, ocupacion);
    }

    @Override
    public void showResponse(boolean success) {
        view.showResponse(success);
    }

    @Override
    public void getCliente(String cedula) {
        interactor.getCliente(cedula);
    }

    @Override
    public void setCliente(Cliente cliente) {
        view.setCliente(cliente);
    }
}
