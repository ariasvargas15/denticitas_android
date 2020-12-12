package com.amongusdev.denticitas.cliente.perfil.interfaces;

import com.amongusdev.denticitas.model.entities.Cliente;

import java.util.Date;

public interface IPerfil {
    interface View {
        void showResponse(boolean success);
        void setCliente(Cliente cliente);
    }

    interface Presenter {
        void setDatosPersonales(String cedula, String nombre, String apellido, Date fechaNacimiento,
                                String telefono, String direccion, String email, String ocupacion);
        void showResponse(boolean success);
        void getCliente(String cedula);
        void setCliente(Cliente cliente);
    }

    interface Interactor {
        void setDatosPersonales(String cedula, String nombre, String apellido, Date fechaNacimiento,
                                String telefono, String direccion, String email, String ocupacion);
        void getCliente(String cedula);
    }
}
