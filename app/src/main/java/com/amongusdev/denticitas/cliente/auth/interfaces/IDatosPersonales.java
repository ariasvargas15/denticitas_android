package com.amongusdev.denticitas.cliente.auth.interfaces;

import java.util.Date;

public interface IDatosPersonales {
    interface View {
        void showResponse(boolean success);
    }

    interface Presenter {
        void setDatosPersonales(String cedula, String nombre, String apellido, Date fechaNacimiento,
                                String telefono, String direccion, String email, String ocupacion);
        void showResponse(boolean success);
    }

    interface Interactor {
        void setDatosPersonales(String cedula, String nombre, String apellido, Date fechaNacimiento,
                                String telefono, String direccion, String email, String ocupacion);
    }
}
