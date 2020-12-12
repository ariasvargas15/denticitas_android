package com.amongusdev.denticitas.cliente.agendar.view;

import android.app.AlertDialog;
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

import com.amongusdev.denticitas.DashboardActivity;
import com.amongusdev.denticitas.R;
import com.amongusdev.denticitas.cliente.agendar.interfaces.IAgendar;
import com.amongusdev.denticitas.cliente.agendar.presenter.AgendarPresenter;
import com.amongusdev.denticitas.cliente.auth.view.LoginActivity;
import com.amongusdev.denticitas.model.entities.Agenda;
import com.amongusdev.denticitas.model.entities.DiaAgenda;
import com.amongusdev.denticitas.model.entities.Especialista;
import com.amongusdev.denticitas.model.entities.Servicio;
import com.amongusdev.denticitas.utils.Utils;
import com.google.android.material.textfield.TextInputEditText;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;

public class AgendarFragment extends Fragment implements IAgendar.View, AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener {


    @BindView(R.id.spinner_especialistas)
    Spinner spinner;
    @BindView(R.id.input_fecha)
    TextInputEditText fecha;


    IAgendar.Presenter presenter;
    NavController navController;
    String arg1 = "servicio";
    Servicio servicio;
    ArrayList<Especialista> especialistas;
    Calendar myCalendar = Calendar.getInstance();
    ArrayList<Calendar> diasHabiles = new ArrayList<>();
    ArrayList<Agenda> agenda;
    DiaAgenda diaAgenda;
    AlertDialog dialog;
    public AgendarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            servicio = (Servicio) getArguments().getSerializable(arg1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_agendar, container, false);
        ButterKnife.bind(this, v);
        presenter = new AgendarPresenter(this);
        SpotsDialog.Builder sp = new SpotsDialog.Builder();
        sp.setContext(getContext()).setCancelable(false).setMessage("Loading...");
        dialog = sp.build();
        dialog.show();
        presenter.getEspecialistas(servicio.getArea().getId());
        spinner.setOnItemSelectedListener(this);

        //Convierto la variable List<> en un ArrayList<>()

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    @OnClick(R.id.confirmar_fecha)
    public void confirmarFecha() {
        getDiaAgenda();
        Bundle b = new Bundle();
        b.putSerializable("date", myCalendar);
        b.putSerializable("servicio", servicio);
        b.putSerializable("agenda", diaAgenda);
        navController.navigate(R.id.action_nav_agendar_to_nav_turno, b);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        fecha.setText("");
        fecha.setEnabled(true);
        dialog.show();
        presenter.getAgendas(especialistas.get(position).getCedula());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void setEspecialistas(ArrayList<Especialista> especialistas) {
        this.especialistas = especialistas;
        ArrayList<String> array = new ArrayList<>();
        for (Especialista e : especialistas){
            array.add(e.getPersona().getNombre() + " " + e.getPersona().getApellido());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, array);
        this.spinner.setAdapter(adapter);
        dialog.dismiss();
    }

    @Override
    public void setAgendas(ArrayList<Agenda> agendas) {
        this.agenda = agendas;
        diasHabiles = new ArrayList<>();
        for (Agenda a : agendas){
            for (DiaAgenda d : a.getDiaAgendaList()){
                diasHabiles.add(setDate(a.getAnio(), a.getMes() - 1, d.getDia()));
            }
        }
        if (diasHabiles.isEmpty()){
            fecha.setEnabled(false);
        }
        dialog.dismiss();
    }

    private Calendar setDate(int year, int month, int day){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DATE, day);
        return c;
    }

    @OnClick(R.id.input_fecha)
    public void onClickFecha(){
        showDatePicker();
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);
        fecha.setText(sdf.format(myCalendar.getTime()));
    }

    private void showDatePicker() {

        Calendar calendar = Calendar.getInstance();

        DatePickerDialog dpd = DatePickerDialog.newInstance(
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getParentFragmentManager(), "DatePickerDialog");
        Calendar[] d = diasHabiles.toArray(new Calendar[diasHabiles.size()]);
        dpd.setSelectableDays(d);

    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DATE, dayOfMonth);
            updateLabel();
    }

    private void getDiaAgenda(){
        for (Agenda a : agenda){
            for (DiaAgenda d : a.getDiaAgendaList()){
                if (a.getAnio() == myCalendar.get(Calendar.YEAR)
                && a.getMes() == myCalendar.get(Calendar.MONTH) + 1
                && d.getDia() == myCalendar.get(Calendar.DATE)){
                    diaAgenda = d;
                    break;
                }
            }
        }
    }


}