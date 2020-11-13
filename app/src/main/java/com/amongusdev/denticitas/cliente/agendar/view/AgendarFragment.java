package com.amongusdev.denticitas.cliente.agendar.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.amongusdev.denticitas.R;
import com.amongusdev.denticitas.cliente.agendar.interfaces.IAgendar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AgendarFragment extends Fragment implements IAgendar.View, AdapterView.OnItemSelectedListener {


    @BindView(R.id.spinner_especialistas)
    Spinner especialistas;

    NavController navController;

    public AgendarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_agendar, container, false);
        ButterKnife.bind(this, v);

        especialistas.setOnItemSelectedListener(this);
        //Convierto la variable List<> en un ArrayList<>()
        ArrayList<String> array = new ArrayList<>();
        array.add("Especialista 1");
        array.add("Especialista 2");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, array);
        especialistas.setAdapter(adapter);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    @OnClick(R.id.confirmar_fecha)
    public void confirmarFecha(){
        navController.navigate(R.id.action_nav_agendar_to_nav_turno);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}