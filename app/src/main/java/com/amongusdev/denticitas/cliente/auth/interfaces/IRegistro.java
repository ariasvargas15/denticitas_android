package com.amongusdev.denticitas.cliente.auth.interfaces;

public interface IRegistro {
    interface View {
        void confirmarRegistro(boolean bool);
    }
    interface Presenter{
        void registrarse(String user, String password, String tipo);
        void confirmarRegistro(boolean bool);
    }
    interface Interactor{
        void registrarse(String user, String password, String tipo);
    }
}
