package com.amongusdev.denticitas.cliente.servicios.interfaces;

import com.amongusdev.denticitas.model.entities.Servicio;

import java.util.ArrayList;

public interface IServicios {
    interface View {
        void mostrarServicios(ArrayList<Servicio> servicios);
    }
    interface Presenter{
        void buscarServicios();
        void mostrarServicios(ArrayList<Servicio> servicios);
    }
    interface Interactor{
        void buscarServicios();
    }
}
