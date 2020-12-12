package com.amongusdev.denticitas.cliente.auth.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.amongusdev.denticitas.DashboardActivity;
import com.amongusdev.denticitas.R;
import com.amongusdev.denticitas.cliente.auth.interfaces.IDatosPersonales;
import com.amongusdev.denticitas.cliente.auth.presenter.DatosPersonalesPresenter;
import com.amongusdev.denticitas.utils.Utils;
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

public class DatosPersonalesActivity extends AppCompatActivity implements IDatosPersonales.View {

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
    @BindView(R.id.completar)
    LinearLayout completar;

    IDatosPersonales.Presenter presenter;
    Calendar myCalendar = Calendar.getInstance();
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_personales);
        ButterKnife.bind(this);
        presenter = new DatosPersonalesPresenter(this);
        instanciarCalendar();
    }

    @OnClick(R.id.btn_datos)
    public void onClickCompletarDatos() {
        if(validarInputs()){
            String cedula = Utils.getValuePreference(getApplicationContext(), "auth");
            String nombre = this.nombre.getText().toString();
            String apellido = this.apellido.getText().toString();
            Date fecha = myCalendar.getTime();
            String telefono = this.telefono.getText().toString();
            String direccion = this.direccion.getText().toString();
            String email = this.email.getText().toString();
            String ocupacion = this.ocupacion.getText().toString();
            SpotsDialog.Builder sp = new SpotsDialog.Builder();
            sp.setContext(DatosPersonalesActivity.this).setCancelable(false).setMessage("Loading...");
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
            Snackbar.make(completar, "Digite todos los campos", Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (!validarEmail(email.getText().toString())) {
            Snackbar.make(completar, "Email no válido", Snackbar.LENGTH_SHORT).show();
            return false;
        } else {
           Calendar c = (Calendar) myCalendar.clone();
           c.add(Calendar.YEAR, 18);
           if (c.after(Calendar.getInstance())){
               Snackbar.make(completar, "Debes ser mayor de edad", Snackbar.LENGTH_SHORT).show();
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
            new DatePickerDialog(DatosPersonalesActivity.this, dat, myCalendar
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

    @Override
    public void showResponse(boolean success) {
        dialog.dismiss();
        if (success){
            Utils.goToNextActivityCleanStack(this, DashboardActivity.class, true, null);
        }
    }
}