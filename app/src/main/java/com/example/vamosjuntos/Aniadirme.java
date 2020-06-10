package com.example.vamosjuntos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vamosjuntos.Evento.ListadoEvento;

public class Aniadirme extends AppCompatActivity {

    Button añadirme;
    EditText numPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniadirme);

        añadirme = (Button) findViewById(R.id.buttonAniadirmeListadoEvento);
        //numPeople = (EditText) findViewById(R.id.editText6);

        añadirme.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ListadoEvento.class);
                startActivity(intent);
            }
        });
    }


}
