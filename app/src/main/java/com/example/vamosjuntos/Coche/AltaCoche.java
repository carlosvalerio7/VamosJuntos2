package com.example.vamosjuntos.Coche;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vamosjuntos.R;
import com.google.gson.Gson;

public class AltaCoche extends AppCompatActivity {
    //VARIABLES
    Button registrar, fotoCarnet, fotoCoche;
    EditText plazas, direccionSalida, telefono;
    private static final int PICK_IMAGE_REQUEST_CARNET = 1;
    Uri imagenCarnet, imagenCoche;
    private static final int PICK_IMAGE_REQUEST_COCHE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_coche);
        //INSTANCIAMOS LAS VARIABLES Y ASI LOGRAR RELACIONARLAS CON LAS VISTAS DEL LAYOUT
        registrar = (Button) findViewById(R.id.buttonRegistrar);
        plazas = (EditText) findViewById(R.id.editTextPlazasDisponibles);
        direccionSalida = (EditText) findViewById(R.id.editTexDireccion);
        telefono = (EditText) findViewById(R.id.editTextTelefono);
        fotoCarnet = (Button) findViewById(R.id.buttonFotoCarnet);
        fotoCoche = (Button) findViewById(R.id.buttonFotoCoche);

        registrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                registrarCoche();
            }
        });

        //INTENTS PARA BUSCAR UNA FOTO EN EL ALMACEN DE FOTOS DEL DISPOSITIVO
        fotoCarnet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST_CARNET);
                imagenCarnet = intent.getData();
            }
        });

        fotoCoche.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST_COCHE);
                imagenCoche = intent.getData();
            }
        });
    }

    public void registrarCoche() {
        //CONDICIONES QUE DEBEN CUMPLIRSE PARA CADA CAMPO DEL REGISTRO DE UN COCHE
        //Y SALTO DE UN MENSAJE SI NO SE CUMPLEN LAS CONDICIONES
        if (plazas.getText().toString().length()==0) {
            Toast.makeText(getApplicationContext(), "Introduzca el numero de plazas", Toast.LENGTH_SHORT).show();
        } else if (direccionSalida.getText().toString().length()==0) {
            Toast.makeText(getApplicationContext(), "Falta una dirección de salida", Toast.LENGTH_SHORT).show();
        } else if (telefono.getText().toString().length()==0) {
            Toast.makeText(getApplicationContext(), "Falta el telefono", Toast.LENGTH_SHORT).show();
        } else if (imagenCarnet == null) {
            Toast.makeText(getApplicationContext(), "Falta una imagen del carnet ", Toast.LENGTH_SHORT).show();
        } else if (imagenCoche == null) {
            Toast.makeText(getApplicationContext(), "Falta una imagen del coche", Toast.LENGTH_SHORT).show();
        } else {
            //COGEMOS LA VARIABLE ESPECIFICADA DEL  OBJETO DE LA CLASE COCHE Y LO TRANSFORMAMOS EN UNA CADENA DE TEXTO
            Coche c = new Coche(Integer.parseInt(plazas.getText().toString()), direccionSalida.getText().toString(), telefono.getText().toString());
            //CREAMOS UNA INSTANCIA DE GSON Y AL METODO toJson LE PASAMOS EL OBJETO QUE
            //QUEREMOS SERIALIZAR EN ESTE CASO EVENTO
            Gson gson = new Gson();
            String JSON = gson.toJson(c);

            //INTENT PARA CAMBIAR DE PANTALLA DESPUES DE CREAR UN COCHE Y MOSTRAR SU DETALLE
            Intent activity2Intent = new Intent(getApplicationContext(), DetalleCoche.class);
            activity2Intent.putExtra("cocheJson", JSON);
            startActivity(activity2Intent);
        }
    }
}
