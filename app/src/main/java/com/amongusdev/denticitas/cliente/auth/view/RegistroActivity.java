package com.amongusdev.denticitas.cliente.auth.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.amongusdev.denticitas.R;
import com.amongusdev.denticitas.cliente.auth.interfaces.IRegistro;
import com.amongusdev.denticitas.utils.Utils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistroActivity extends AppCompatActivity implements IRegistro.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_inicia_sesion)
    public void onClickInicio(View view){
        finish();
    }

    @OnClick(R.id.btn_registro)
    public void onClickRegistro(View view){
        Utils.goToNextActivityCleanStack(this, DatosPersonalesActivity.class, false, null);
    }
}