package com.amongusdev.denticitas.cliente.auth.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.amongusdev.denticitas.DashboardActivity;
import com.amongusdev.denticitas.R;
import com.amongusdev.denticitas.cliente.auth.interfaces.IRegistro;
import com.amongusdev.denticitas.cliente.auth.presenter.RegistroPresenter;
import com.amongusdev.denticitas.utils.Utils;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistroActivity extends AppCompatActivity implements IRegistro.View {


    @BindView(R.id.input_cedula_registro)
    TextInputEditText cedula;
    @BindView(R.id.input_pwd_registro)
    TextInputEditText password;
    @BindView(R.id.input_pwd_confirmar_registro)
    TextInputEditText confirmar;
    @BindView(R.id.registro)
    LinearLayout registro;

    IRegistro.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        ButterKnife.bind(this);
        presenter = new RegistroPresenter(this, getApplicationContext());
    }

    @OnClick(R.id.btn_inicia_sesion)
    public void onClickInicio(){
        finish();
    }

    @OnClick(R.id.btn_registro)
    public void onClickRegistro(){
        if (cedula.getText()!=null && password.getText()!=null && confirmar.getText()!= null
                && !cedula.getText().toString().isEmpty() && !password.getText().toString().isEmpty() && !confirmar.getText().toString().isEmpty()){
            if (password.getText().toString().equals(confirmar.getText().toString())){
                presenter.registrarse(cedula.getText().toString(), password.getText().toString(), "cliente");
            } else {
                Snackbar.make(registro, "Las contraseñas no coinciden", Snackbar.LENGTH_SHORT).show();
            }
        } else {
            Snackbar.make(registro, "Digite los datos correctamente", Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void confirmarRegistro(boolean bool) {
        if (bool){
            Utils.goToNextActivityCleanStack(this, DatosPersonalesActivity.class, true, null);
        } else {
            Snackbar.make(registro, "Este usuario ya existe", Snackbar.LENGTH_SHORT).show();
        }
    }
}