package com.amongusdev.denticitas.cliente.auth.presenter;

import android.content.Context;

import com.amongusdev.denticitas.cliente.auth.interactor.RegistroInteractor;
import com.amongusdev.denticitas.cliente.auth.interfaces.IRegistro;

public class RegistroPresenter implements IRegistro.Presenter {

    IRegistro.Interactor interactor;
    IRegistro.View view;

    public RegistroPresenter(IRegistro.View view, Context context) {
        this.view = view;
        this.interactor = new RegistroInteractor(this, context);
    }

    @Override
    public void registrarse(String user, String password, String tipo) {
        interactor.registrarse(user, password, tipo);
    }

    @Override
    public void confirmarRegistro(boolean bool) {
        view.confirmarRegistro(bool);
    }
}
