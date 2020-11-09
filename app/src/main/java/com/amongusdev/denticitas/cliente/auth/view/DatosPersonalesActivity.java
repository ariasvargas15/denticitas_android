package com.amongusdev.denticitas.cliente.auth.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.amongusdev.denticitas.R;
import com.amongusdev.denticitas.cliente.auth.interfaces.IDatosPersonales;

public class DatosPersonalesActivity extends AppCompatActivity implements IDatosPersonales.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_personales);
    }
}