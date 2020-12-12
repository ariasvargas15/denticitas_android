package com.amongusdev.denticitas.cliente.perfil.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.amongusdev.denticitas.DashboardActivity;
import com.amongusdev.denticitas.R;
import com.amongusdev.denticitas.cliente.auth.interfaces.IDatosPersonales;
import com.amongusdev.denticitas.cliente.auth.view.DatosPersonalesActivity;
import com.amongusdev.denticitas.cliente.perfil.interfaces.IPerfil;
import com.amongusdev.denticitas.cliente.perfil.presenter.PerfilPresenter;
import com.amongusdev.denticitas.model.entities.Cliente;
import com.amongusdev.denticitas.utils.Utils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;


public class PerfilFragment extends Fragment implements IPerfil.View {


    @BindView(R.id.input_nombre_datos)
    TextInputEditText nombre;
    @BindView(R.id.input_apellido_datos)
    TextInputEditText apellido;
    @BindView(R.id.input_fecha_datos)
    TextInputEditText fecha;
    @BindView(R.id.input_telefono_datos)
    TextInputEditText telefono;
    @BindView(R.id.input_direccion_datos)
    TextInputEditText direccion;
    @BindView(R.id.input_email_datos)
    TextInputEditText email;
    @BindView(R.id.input_ocupacion_datos)
    TextInputEditText ocupacion;
    @BindView(R.id.perfil)
    ScrollView perfil;
    @BindView(R.id.btn_datos)
    MaterialButton actualizar;

    private IPerfil.Presenter presenter;
    private Calendar myCalendar = Calendar.getInstance();
    private AlertDialog dialog;
    private Context context;
    private DashboardActivity activity;


    public PerfilFragment() {
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);
        ButterKnife.bind(this, v);
        context = getActivity().getApplicationContext();
        activity = (DashboardActivity) getActivity();
        presenter = new PerfilPresenter(this);
        presenter.getCliente(Utils.getValuePreference(context, "auth"));
        enableButtons();
        return v;
    }

    @OnClick(R.id.btn_edit)
    public void editButtons(){
      enableButtons();
    }

    @OnClick(R.id.btn_datos)
    public void actualizarDatos(){
        if(validarInputs()){
            String cedula = Utils.getValuePreference(context, "auth");
            String nombre = this.nombre.getText().toString();
            String apellido = this.apellido.getText().toString();
            Date fecha = myCalendar.getTime();
            String telefono = this.telefono.getText().toString();
            String direccion = this.direccion.getText().toString();
            String email = this.email.getText().toString();
            String ocupacion = this.ocupacion.getText().toString();
            SpotsDialog.Builder sp = new SpotsDialog.Builder();
            sp.setContext(getActivity()).setCancelable(false).setMessage("Loading...");
            dialog = sp.build();
            dialog.show();
            presenter.setDatosPersonales(cedula, nombre, apellido, fecha, telefono, direccion, email, ocupacion);
        }
    }

    private boolean validarInputs() {
        if (nombre.getText() == null || nombre.getText().toString().isEmpty()
                || apellido.getText() == null || apellido.getText().toString().isEmpty()
                || fecha.getText() == null || fecha.getText().toString().isEmpty()
                || telefono.getText() == null || telefono.getText().toString().isEmpty()
                || direccion.getText() == null || direccion.getText().toString().isEmpty()
                || email.getText() == null || email.getText().toString().isEmpty()
                || ocupacion.getText() == null || ocupacion.getText().toString().isEmpty()
        ) {
            Snackbar.make(perfil, "Digite todos los campos", Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (!validarEmail(email.getText().toString())) {
            Snackbar.make(perfil, "Email no válido", Snackbar.LENGTH_SHORT).show();
            return false;
        } else {
            Calendar c = (Calendar) myCalendar.clone();
            c.add(Calendar.YEAR, 18);
            if (c.after(Calendar.getInstance())){
                Snackbar.make(perfil, "Debes ser mayor de edad", Snackbar.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    private void instanciarCalendar() {
        DatePickerDialog.OnDateSetListener dat = (view, year, monthOfYear, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };
        fecha.setOnClickListener(v -> {
            new DatePickerDialog(getActivity(), dat, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);
        fecha.setText(sdf.format(myCalendar.getTime()));
    }

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    private void enableButtons(){
        boolean b = !nombre.isEnabled();
        int color =  b ? R.color.Black : R.color.grey;
        int r = getResources().getColor(color, getActivity().getTheme());
        nombre.setEnabled(b);
        nombre.setTextColor(r);
        apellido.setEnabled(b);
        apellido.setTextColor(r);
        fecha.setEnabled(b);
        fecha.setTextColor(r);
        telefono.setEnabled(b);
        telefono.setTextColor(r);
        direccion.setEnabled(b);
        direccion.setTextColor(r);
        email.setEnabled(b);
        email.setTextColor(r);
        ocupacion.setEnabled(b);
        ocupacion.setTextColor(r);
        actualizar.setEnabled(b);
        if (!b) actualizar.setBackgroundColor(r);
        else actualizar.setBackgroundColor(getResources().getColor(R.color.colorPrimary, getActivity().getTheme()));
    }

    @Override
    public void showResponse(boolean success) {
        dialog.dismiss();
        if (success){
            Snackbar.make(perfil, "Usuario actualizado correctamente", Snackbar.LENGTH_SHORT).show();
            enableButtons();
        }

    }

    @Override
    public void setCliente(Cliente cliente) {
        nombre.setText(cliente.getPersona().getNombre());
        apellido.setText(cliente.getPersona().getApellido());
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);
        fecha.setText(sdf.format(cliente.getPersona().getFechaNacimiento()));
        myCalendar.setTime(cliente.getPersona().getFechaNacimiento());
        instanciarCalendar();
        telefono.setText(cliente.getPersona().getTelefono());
        direccion.setText(cliente.getPersona().getDireccion());
        email.setText(cliente.getPersona().getEmail());
        ocupacion.setText(cliente.getOcupacion());


    }
}