package com.amongusdev.denticitas.cliente.servicios.presenter;

import com.amongusdev.denticitas.cliente.servicios.interactor.ServiciosInteractor;
import com.amongusdev.denticitas.cliente.servicios.interfaces.IServicios;
import com.amongusdev.denticitas.model.entities.Servicio;

import java.util.ArrayList;

public class ServiciosPresenter implements IServicios.Presenter {

    private IServicios.View view;
    private IServicios.Interactor interactor;

    public ServiciosPresenter(IServicios.View view){
        this.view = view;
    }

    @Override
    public void buscarServicios() {
        interactor = new ServiciosInteractor(this);
        interactor.buscarServicios();
    }

    @Override
    public void mostrarServicios(ArrayList<Servicio> servicios) {
        view.mostrarServicios(servicios);
    }
}
