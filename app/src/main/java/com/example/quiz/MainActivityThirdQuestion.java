package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivityThirdQuestion extends AppCompatActivity {

    private int puntuacion;
    private ListView lista;
    private ArrayList<String> respuestas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_third_question);

        Bundle datos = this.getIntent().getExtras();
        puntuacion = datos.getInt("puntuacion");
        TextView tv = (TextView) findViewById(R.id.puntuacion3);
        tv.setText("Puntuacion:"+this.puntuacion);


        lista = findViewById(R.id.ListaRespuestas);

        respuestas = new ArrayList<>();
        respuestas.add("8");
        respuestas.add("7");
        respuestas.add("6");
        respuestas.add("3");

        ArrayAdapter adaptadorRespuestas = new ArrayAdapter(this,android.R.layout.simple_list_item_1,respuestas);

        lista.setAdapter(adaptadorRespuestas);

        lista.setOnItemClickListener((AdapterView.OnItemClickListener) this);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id){

        String respuestasCadena = respuestas.get(position);
        Log.i("RESPUESTA","Respuesta seleccionada:" + respuestas);
    }

    public int getPuntuacion() {
        return puntuacion;
    }
}