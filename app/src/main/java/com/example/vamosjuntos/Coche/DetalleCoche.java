package com.example.vamosjuntos.Coche;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vamosjuntos.Aniadirme;
import com.example.vamosjuntos.R;
import com.google.gson.Gson;

public class DetalleCoche extends AppCompatActivity {
    //VARIABLES
    Button aniadirme;
    TextView plazas, direccionSalida, telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_coche);
        //INSTANCIAMOS LAS VARIABLES Y ASI LOGRAR RELACIONARLAS CON LAS VISTAS DEL LAYOUT
        aniadirme = (Button) findViewById(R.id.buttonDetalleAniadirme);
        plazas = (TextView) findViewById(R.id.textViewDetallePlazasCoche2);
        direccionSalida = (TextView) findViewById(R.id.textViewDetalleDireccionCoche2);
        telefono = (TextView) findViewById(R.id.textViewDetalleTelefonoCoche2);

        //CREAMOS UNA INSTANCIA DEL GSON Y USAMOS SU METODO fromJson AL CUAL LE PASAREMOS
        //EL STRING(cocheJson) Y LA CLASE EN LA QUE SE DESERIALIZARA
        Gson gson = new Gson();
        Coche c = gson.fromJson(getIntent().getStringExtra("cocheJson"), Coche.class);
        //MOSTRAMOS LOS DATOS UNA VEZ DESERIALIZADOS

        telefono.setText("" + c.getTelefono());
        plazas.setText("" + c.getPlazas());
        direccionSalida.setText("" + c.getDireccionSalida());

        //INTENT PARA PASAR A OTRA PANTALLA DE LA APP UNA VEZ HAYAS DECIDIDO AÑADIRTE AL VIAJE
        aniadirme.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Aniadirme.class);
                startActivity(intent);
            }
        });
    }
}
