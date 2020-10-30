package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityFourthQuestion extends AppCompatActivity {
    private int puntuacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fourth_question);

        Bundle datos = this.getIntent().getExtras();
        puntuacion = datos.getInt("puntuacion");
        TextView tv = (TextView) findViewById(R.id.Puntuacion);
        tv.setText("Puntuacion:" + this.puntuacion);
    }

    public void respuesta4(View view) {

        RadioButton idR = (RadioButton) findViewById(R.id.respuestaCorrecta4);
        if (idR.isChecked()) {
            puntuacion += 3;
            Toast.makeText(this, "Respuesta correcta", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Respuesta incorrecta ", Toast.LENGTH_LONG).show();
            puntuacion -= 2;
        }

        Intent intent = new Intent(this, MainActivityFifthQuestion.class);
        intent.putExtra("puntuacion", getPuntuacion());
        startActivity(intent);
    }
    public int getPuntuacion() {
        return puntuacion;
    }
}