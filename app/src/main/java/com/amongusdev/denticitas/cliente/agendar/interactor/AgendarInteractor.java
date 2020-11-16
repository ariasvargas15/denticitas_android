package com.amongusdev.denticitas.cliente.agendar.interactor;

import android.util.Log;

import com.amongusdev.denticitas.cliente.agendar.interfaces.IAgendar;
import com.amongusdev.denticitas.model.apiservice.ApiAdapter;
import com.amongusdev.denticitas.model.entities.Agenda;
import com.amongusdev.denticitas.model.entities.Especialista;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgendarInteractor implements IAgendar.Interactor {

    IAgendar.Presenter presenter;
    ArrayList<Especialista> especialistas;
    ArrayList<Agenda> agendas;


    public AgendarInteractor(IAgendar.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getEspecialistas(int area) {
        Call<List<Especialista>> call = ApiAdapter.getApiService().getEspecialistas(area);
        call.enqueue(new Callback<List<Especialista>>() {
            @Override
            public void onResponse(Call<List<Especialista>> call, Response<List<Especialista>> response) {
                if (response.isSuccessful()) {
                    especialistas = (ArrayList<Especialista>) response.body();
                    if (especialistas != null) {
                        presenter.setEspecialistas(especialistas);
                    } else {
                        Log.e("getEspecialistas", "Response is null");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Especialista>> call, Throwable t) {
                presenter.setEspecialistas(new ArrayList<>());
                Log.e("getEspecialistas", "Response failed");
            }
        });
    }

    @Override
    public void getAgendas(String cedula) {
        Call<List<Agenda>> call = ApiAdapter.getApiService().getAgendaEspecialista(cedula);
        call.enqueue(new Callback<List<Agenda>>() {
            @Override
            public void onResponse(Call<List<Agenda>> call, Response<List<Agenda>> response) {
                if (response.isSuccessful()) {
                    agendas = (ArrayList<Agenda>) response.body();
                    if (agendas != null) {
                        presenter.setAgendas(agendas);
                    } else {
                        Log.e("getAgendas", "Response is null");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Agenda>> call, Throwable t) {
                presenter.setAgendas(new ArrayList<>());
                Log.e("getAgendas", "Response failed");
            }
        });
    }
}
