package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

public class MainActivityFifthQuestion extends AppCompatActivity {
    private int puntuacion;
    private String name;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fifth_question);

        Bundle datos = this.getIntent().getExtras();
        puntuacion = datos.getInt("puntuacion");
        name = datos.getString("name");
        TextView tv = (TextView) findViewById(R.id.Puntuacion);
        tv.setText("Puntuación: " + this.puntuacion);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    public void respuesta5(View view) throws InterruptedException {

        RadioButton idR = (RadioButton) findViewById(R.id.respuestaCorrecta5);
        RadioButton idR2 = (RadioButton) findViewById(R.id.Respuestaincorrecta5_1);
        RadioButton idR3 = (RadioButton) findViewById(R.id.respuestaInorrecta5_2);
        RadioButton idR4 = (RadioButton) findViewById(R.id.respuestaincorrecta5_3);
        MediaPlayer mpfail = MediaPlayer.create(this, R.raw.fail);
        MediaPlayer mpcorrect = MediaPlayer.create(this, R.raw.correct);
        if (idR.isChecked()) {
            puntuacion += 3;
            mpcorrect.start();
            Thread.sleep(1000);
            Toast.makeText(this, "Respuesta correcta", Toast.LENGTH_LONG).show();        Intent intent = new Intent(this, MainActivityFinalScore.class);
            intent.putExtra("puntuacion", getPuntuacion());
            intent.putExtra("name",name);
            startActivity(intent);
            finish();
        } else if(!idR.isChecked() && !idR2.isChecked() && !idR4.isChecked() & !idR3.isChecked()){
            mpfail.start();
            Toast.makeText(this, "Marque una respuesta", Toast.LENGTH_LONG).show();
        }
        else {
            mpfail.start();
            Thread.sleep(1000);
            Toast.makeText(this, "Respuesta incorrecta ", Toast.LENGTH_LONG).show();
            if(puntuacion<=2){
                puntuacion=0;
                Intent intent = new Intent(this, MainActivityFinalScore.class);
                intent.putExtra("puntuacion", getPuntuacion());
                intent.putExtra("name",name);
                startActivity(intent);
                finish();
            }else {
                puntuacion -= 2;
                Intent intent = new Intent(this, MainActivityFinalScore.class);
                intent.putExtra("puntuacion", getPuntuacion());
                intent.putExtra("name",name);
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