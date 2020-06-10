package com.example.vamosjuntos.Coche;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class ListadoCoche extends AppCompatActivity {
    //VARIABLES
    List<Coche> listaCoches = new ArrayList<Coche>();
    ListView listViewCoches;
    TextView contarResultados;
    EditText buscador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_coche);
        //INSTANCIAMOS LAS VARIABLES Y ASI LOGRAR RELACIONARLAS CON LAS VISTAS DEL LAYOUT
        buscador = (EditText) findViewById(R.id.editText6ListadoCochesBbuscar);
        contarResultados = (TextView) findViewById(R.id.textViewListadoCochesContar);

        mostrarListado();

        listViewCoches.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //CREAMOS UNA INSTANCIA DEL GSON Y USAMOS EL METODO toJson PARA SERIALIZAR
                //LA LISTA CON EL OBJETO COCHE
                Gson gson = new Gson();
                String JSON = gson.toJson(listaCoches.get(position));

                //INTENT PARA PASAR LOS DATOS DE COCHE DE LA LISTA AL DETALLE
                Intent activity2Intent = new Intent(getApplicationContext(), DetalleCoche.class);
                activity2Intent.putExtra("cocheJson", JSON);
                startActivity(activity2Intent);

            }
        });
    }

    public void mostrarListado() {
        String json = "";
        try {
            //BUSCAMOS EN LA CARPETA ASSETS Y ABRIMOS EL FICHERO ESPECIFICADO
            InputStream stream = getAssets().open("coche.json");
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer); //LEEMOS EL BUFFER BYTE A BYTE
            stream.close();//CERRAMOS EL FICHERO json
            json = new String(buffer);
        } catch (Exception e) {
        }

        listaCoches = Arrays.asList(new Gson().fromJson(json, Coche[].class));

        String[] arrayTitulo = new String[listaCoches.size()];
        for (int i = 0; i < listaCoches.size(); i++) {
            arrayTitulo[i] = listaCoches.get(i).getDireccionSalida() + " / " + listaCoches.get(i).getPlazas();
        }

        listViewCoches = (ListView) findViewById(R.id.ListadoCochesCoches);

        //DESPLEGAREMOS LA LISTA CON LA DIRECCION DE SALIDA SOLAMENTE
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayTitulo);
        listViewCoches.setAdapter(adapter);

        //CONFIGURACION DEL BUSCADOR
        buscador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        contarResultados.setText(Integer.toString(listaCoches.size()) + " Resultados encontrados");
    }
}
