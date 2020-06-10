package com.example.vamosjuntos.Evento;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vamosjuntos.Coche.AltaCoche;
import com.example.vamosjuntos.Coche.ListadoCoche;
import com.example.vamosjuntos.R;
import com.google.gson.Gson;

public class DetalleEvento extends AppCompatActivity {
    //VARIABLES
    Button buttonListaCoche, buttonAltaCoche;
    TextView nombre, direccion, fecha, hora, informacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_evento);

        //INSTANCIAMOS LAS VARIABLES Y ASI LOGRAR RELACIONARLAS CON LAS VISTAS DEL LAYOUT
        nombre = (TextView) findViewById(R.id.textViewDetalleNombreEvento);
        direccion = (TextView) findViewById(R.id.textViewDetalleDireccionEvento2);
        fecha = (TextView) findViewById(R.id.textViewDetalleFechaEvento2);
        hora = (TextView) findViewById(R.id.textViewDetalleHoraEvento2);
        informacion = (TextView) findViewById(R.id.textViewDetalleDescripcionEvento2);
        buttonListaCoche = (Button) findViewById(R.id.buttonDetalleListaCoches);
        buttonAltaCoche = (Button) findViewById(R.id.buttonDetalleAltaCoche);

        //INTENTS PARA PASAR A OTRA PANTALLA DE LA APP
        buttonAltaCoche.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AltaCoche.class);
                startActivity(intent);
            }
        });

        buttonListaCoche.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListadoCoche.class);
                startActivity(intent);
            }
        });

        //CREAMOS UNA INSTANCIA DEL GSON Y USAMOS SU METODO fromJson AL CUAL LE PASAREMOS
        //EL STRING(eventoJson) Y LA CLASE EN LA QUE SE DESERIALIZARA
        Gson gson = new Gson();
        Evento e = gson.fromJson(getIntent().getStringExtra("eventoJson"), Evento.class);

        //MOSTRAMOS LOS DATOS UNA VEZ DESERIALIZADOS
        nombre.setText("" + e.getNombreEvento());
        direccion.setText("" + e.getDireccionEvento());
        fecha.setText("" + e.getFechaEvento());
        hora.setText("" + e.getHoraEvento());
        informacion.setText("" + e.getDescripcionEvento());
    }
}
