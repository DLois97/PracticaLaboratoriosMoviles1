package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityThirdQuestion extends AppCompatActivity {

    private int puntuacion;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_third_question);

        Bundle datos = this.getIntent().getExtras();
        puntuacion = datos.getInt("puntuacion");
        TextView tv = (TextView) findViewById(R.id.Puntuacion);
        tv.setText("Puntuación: " + this.puntuacion);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void respuesta3(View view) {

        RadioButton idR = (RadioButton) findViewById(R.id.RespuestaCorrecta3);
        RadioButton idR2 = (RadioButton) findViewById(R.id.respuestaincorrecta3_1);
        RadioButton idR3 = (RadioButton) findViewById(R.id.respuestaincorrecta3_2);
        RadioButton idR4 = (RadioButton) findViewById(R.id.respuestaincorrecta3_3);
        if (idR.isChecked()) {
            puntuacion += 3;
            Toast.makeText(this, "Respuesta correcta", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivityFourthQuestion.class);
            intent.putExtra("puntuacion", getPuntuacion());
            startActivity(intent);
            finish();
        } else if (!idR.isChecked() && !idR2.isChecked() && !idR4.isChecked() & !idR3.isChecked()) {
            Toast.makeText(this, "Marque una respuesta", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Respuesta incorrecta ", Toast.LENGTH_LONG).show();
            if (puntuacion <= 2) {
                puntuacion = 0;
                Intent intent = new Intent(this, MainActivityFourthQuestion.class);
                intent.putExtra("puntuacion", getPuntuacion());
                startActivity(intent);
                finish();
            } else {
                puntuacion -= 2;
                Intent intent = new Intent(this, MainActivityFourthQuestion.class);
                intent.putExtra("puntuacion", getPuntuacion());
                startActivity(intent);
                finish();
            }
        }

    }

    public int getPuntuacion() {
        return puntuacion;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if( id == R.id.help) {
            Toast.makeText(this, "HELP", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ManualPregunta.class);
            startActivity(intent);
        }
        return true;
    }
}