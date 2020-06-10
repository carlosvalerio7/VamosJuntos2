package com.example.vamosjuntos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vamosjuntos.Evento.ListadoEvento;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //INSTANCIAMOS LA VARIABLE Y ASI LOGRAR RELACIONARLAS CON LAS VISTAS DEL LAYOUT
        Button button = (Button) findViewById(R.id.buttonMainActivityListaEventos);

        //BUTTON QUE NOS DIRIGE A OTRA PANTALLA DE LA APP
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListadoEvento.class);
                startActivity(intent);
            }
        });
    }
}
