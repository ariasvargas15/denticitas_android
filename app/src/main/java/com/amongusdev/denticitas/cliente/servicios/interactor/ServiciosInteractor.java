package com.amongusdev.denticitas.cliente.servicios.interactor;

import com.amongusdev.denticitas.cliente.servicios.interfaces.IServicios;
import com.amongusdev.denticitas.model.entities.Servicio;

import java.util.ArrayList;

public class ServiciosInteractor implements IServicios.Interactor {

    private ArrayList<Servicio> servicios;
    private IServicios.Presenter presenter;

    public ServiciosInteractor(IServicios.Presenter presenter){
        this.presenter = presenter;
        servicios = new ArrayList<>();
    }

    @Override
    public void buscarServicios() {
        servicios.add(new Servicio("Ortodoncia", "Esta es la descripcion completa de un servicio a realizar necesito un texto larogo de ejemplo", 100000, null));
        servicios.add(new Servicio("Ortodoncia", "Esta es la descripcion completa de un servicio a realizar necesito un texto larogo de ejemplo", 100000, null));
        servicios.add(new Servicio("Ortodoncia", "Esta es la descripcion completa de un servicio a realizar necesito un texto larogo de ejemplo", 100000, null));
        servicios.add(new Servicio("Ortodoncia", "Esta es la descripcion completa de un servicio a realizar necesito un texto larogo de ejemplo", 100000, null));
        servicios.add(new Servicio("Ortodoncia", "Esta es la descripcion completa de un servicio a realizar necesito un texto larogo de ejemplo", 100000, null));
        servicios.add(new Servicio("Ortodoncia", "Esta es la descripcion completa de un servicio a realizar necesito un texto larogo de ejemplo", 100000, null));
        servicios.add(new Servicio("Ortodoncia", "Esta es la descripcion completa de un servicio a realizar necesito un texto larogo de ejemplo", 100000, null));
        servicios.add(new Servicio("Ortodoncia", "Esta es la descripcion completa de un servicio a realizar necesito un texto larogo de ejemplo", 100000, null));
        servicios.add(new Servicio("Ortodoncia", "Esta es la descripcion completa de un servicio a realizar necesito un texto larogo de ejemplo", 100000, null));
        servicios.add(new Servicio("Ortodoncia", "Esta es la descripcion completa de un servicio a realizar necesito un texto larogo de ejemplo", 100000, null));
        presenter.mostrarServicios(servicios);
    }
}
