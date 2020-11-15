package com.amongusdev.denticitas.cliente.auth.interfaces;

public interface ILogin {
    interface View {
        void sendResponse(boolean successful);
    }
    interface Presenter{
        void validateData(String user, String password, String tipo);
        void sendResponse(boolean successful);
    }
    interface Interactor{
        void validateData(String user, String password, String tipo);
    }
}
