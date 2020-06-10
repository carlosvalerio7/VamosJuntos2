package com.example.vamosjuntos.Evento;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vamosjuntos.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AltaEvento extends AppCompatActivity implements View.OnClickListener {

    //VARIABLES
    Button altaButton, fotoButton, fechaButton;
    EditText nombreEvento, direccionEvento, descripcionEvento;
    Spinner horaEvento, minutoEvento;
    int dia, mes, anio;
    String fechaEvento = "";
    private static final int PICK_IMAGE_REQUEST = 1;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_evento);
        //CONFIGURACIÓN SPINNER PARA ESTABLECER UN LIMITE HORARIO DE 24h Y 60m
        //HORA
        List<String> opcionesHora = new ArrayList<String>();
        for (int i = 0; i < 24; i++) {
            opcionesHora.add(Integer.toString(i));
        }
        //MINUTOS
        List<String> opcionesMinuto = new ArrayList<String>();
        for (int i = 0; i < 60; i++) {
            opcionesMinuto.add(Integer.toString(i));
        }
        //INSTANCIAMOS LAS VARIABLES Y ASI LOGRAR RELACIONARLAS CON LAS VISTAS DEL LAYOUT
        Spinner spinner = (Spinner) findViewById(R.id.spinnerHoraEvento);
        Spinner spinner3 = (Spinner) findViewById(R.id.spinnerMinutoEvento);
        altaButton = (Button) findViewById(R.id.buttonRegistrarEvento);
        nombreEvento = (EditText) findViewById(R.id.editTextNombreEvento);
        direccionEvento = (EditText) findViewById(R.id.editTextDireccionEvento);
        descripcionEvento = (EditText) findViewById(R.id.editTextDescripcionEvento);
        horaEvento = (Spinner) findViewById(R.id.spinnerHoraEvento);
        minutoEvento = (Spinner) findViewById(R.id.spinnerMinutoEvento);
        fechaButton = (Button) findViewById(R.id.buttonFechaEvento);
        fotoButton = (Button) findViewById(R.id.buttonFotoEvento);

        //PRETENDEMOS DESPLEGAR UN SPINNER CON LAS OPCIONES DE HORAS Y MINUTOS
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionesHora);
        spinner.setAdapter(adapter);

        ArrayAdapter<String> adapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionesMinuto);
        spinner3.setAdapter(adapter3);

        fotoButton.setOnClickListener(this);
        fechaButton.setOnClickListener(this);

        altaButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //CONDICIONES QUE DEBEN CUMPLIRSE PARA CADA CAMPO DEL REGISTRO DE UN EVENTO
                //Y SALTO DE UN MENSAJE SI NO SE CUMPLEN LAS CONDICIONES
                if (nombreEvento.getText().toString().length()==0) {
                    Toast.makeText(getApplicationContext(), "Falta un nombre", Toast.LENGTH_SHORT).show();
                } else if (direccionEvento.getText().toString().length()==0) {
                    Toast.makeText(getApplicationContext(), "Falta una dirección", Toast.LENGTH_SHORT).show();
                } else if (descripcionEvento.getText().toString().length()==0) {
                    Toast.makeText(getApplicationContext(), "Falta una descripcion", Toast.LENGTH_SHORT).show();
                } else if (fechaEvento.length()==0) {
                    Toast.makeText(getApplicationContext(), "Falta la fecha", Toast.LENGTH_SHORT).show();
                } else if (imageUri == null) {
                    Toast.makeText(getApplicationContext(), "Falta una foto", Toast.LENGTH_SHORT).show();
                } else {

                    //COGEMOS LA VARIABLE ESPECIFICADA DEL  OBJETO DE LA CLASE EVENTO Y LO TRANSFORMAMOS EN UNA CADENA DE TEXTO
                    Evento e = new Evento(nombreEvento.getText().toString(),
                            direccionEvento.getText().toString(),
                            descripcionEvento.getText().toString(),
                            fechaEvento,
                            horaEvento.getSelectedItem().toString() + " : " + minutoEvento.getSelectedItem().toString()
                    );

                    //CREAMOS UNA INSTANCIA DE GSON Y AL METODO toJson LE PASAMOS EL OBJETO QUE
                    //QUEREMOS SERIALIZAR EN ESTE CASO EVENTO
                    Gson gson = new Gson();
                    String json = gson.toJson(e);

                    //INTENT PARA CAMBIAR DE PANTALLA DESPUES DE CREAR UN EVENTO Y MOSTRAR SU DETALLE
                    Intent activity2Intent = new Intent(getApplicationContext(), DetalleEvento.class);
                    activity2Intent.putExtra("eventoJson", json);
                    startActivity(activity2Intent);

                }
            }
        });
    }

    @Override
    public void onClick(View f) {

        //BOTON PARA ESCOGER LA FECHA
        if (f == fechaButton) {
            //INSTANCIAMOS LA CLASE Calendar PARA HACER USO DE SUS METODOS Y ASI OBTENER LAS FECHAS
            //DEL DIA, MES Y AÑO
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            anio = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    fechaButton.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    fechaEvento = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                }
            }, dia, mes, anio);
            datePickerDialog.show();
        }

        //BOTON PARA ESCOGER LA FOTO ALMACENADA EN EL MOVIL
        if (f == fotoButton) {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
            imageUri = intent.getData();
        }
    }
}

