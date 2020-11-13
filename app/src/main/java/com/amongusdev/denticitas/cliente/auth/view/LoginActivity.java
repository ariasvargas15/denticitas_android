package com.amongusdev.denticitas.cliente.auth.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;

import com.amongusdev.denticitas.DashboardActivity;
import com.amongusdev.denticitas.R;
import com.amongusdev.denticitas.utils.Utils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    @OnClick(R.id.btn_login)
    public void onClickLogin(){
        Utils.goToNextActivityCleanStack(this, DashboardActivity.class, false, null);
    }

    @OnClick(R.id.btn_registrate)
    public void onClickRegistro(){
        Utils.goToNextActivityCleanStack(this, RegistroActivity.class, false, null);
    }
}