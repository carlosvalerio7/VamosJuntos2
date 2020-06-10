package com.example.vamosjuntos.Evento;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vamosjuntos.R;
import com.google.gson.Gson;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListadoEvento extends AppCompatActivity {
    //VARIABLES
    List<Evento> listaEventos = new ArrayList<Evento>();
    ListView listViewEventos;
    TextView contarResultados;
    EditText buscador;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_evento);
        //INSTANCIAMOS LAS VARIABLES Y ASI LOGRAR RELACIONARLAS CON LAS VISTAS DEL LAYOUT
        buscador = (EditText) findViewById(R.id.editTextListadoEventoBuscar);
        Button button = (Button) findViewById(R.id.buttonListadoEventoCrearEvento);
        contarResultados = (TextView) findViewById(R.id.textViewListadoEventoContar);

        //INTENT PARA CAMBIAR DE PANTALLA EN LA APP
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AltaEvento.class);
                startActivity(intent);
            }
        });

        mostrarListado();

        listViewEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //CREAMOS UNA INSTANCIA DEL GSON Y USAMOS EL METODO toJson PARA SERIALIZAR
                //LA LISTA CON EL OBJETO EVENTO
                Gson gson = new Gson();
                String json = gson.toJson(listaEventos.get(position));
                //INTENT PARA PASAR LOS DATOS DE LOS EVENTOS DE LA LISTA AL DETALLE
                Intent activity2Intent = new Intent(getApplicationContext(), DetalleEvento.class);
                activity2Intent.putExtra("eventoJson", json);
                startActivity(activity2Intent);
            }
        });
    }

    public void mostrarListado() {
        String json = "";
        try {
            //BUSCAMOS EN LA CARPETA ASSETS Y ABRIMOS EL FICHERO ESPECIFICADO
            InputStream stream = getAssets().open("evento.json");
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer); //LEEMOS EL BUFFER BYTE A BYTE
            stream.close(); //CERRAMOS EL FICHERO json
            json = new String(buffer);
        } catch (Exception e) { }

        //TRANSFORMAMOS EL JSON EN UNA LISTA DE OBJETOS
        listaEventos = Arrays.asList(new Gson().fromJson(json, Evento[].class));

        String[] arrayTitulo = new String[listaEventos.size()];
        for (int i = 0; i < listaEventos.size(); i++) { //RECORREMOS LA LISTA
            arrayTitulo[i] = listaEventos.get(i).getNombreEvento(); //TRANSFORMAMOS LA LISTA DE OBJETOS EN EL nombreEvento
        }

        listViewEventos = (ListView) findViewById(R.id.ListadoEventoEventos);

        //DESPLEGAREMOS LA LISTA CON LOS NOMBRES DEL EVENTO
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayTitulo);
        listViewEventos.setAdapter(adapter);

        //CONFIGURACION DEL BUSCADOR
        buscador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { adapter.getFilter().filter(s); }
            @Override
            public void afterTextChanged(Editable s) { }
        });
        contarResultados.setText((listaEventos.size()) + " Resultados encontrados");
    }
}
