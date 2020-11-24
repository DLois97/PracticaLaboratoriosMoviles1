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

public class MainActivityFourthQuestion extends AppCompatActivity {
    private int puntuacion;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fourth_question);

        Bundle datos = this.getIntent().getExtras();
        puntuacion = datos.getInt("puntuacion");
        TextView tv = (TextView) findViewById(R.id.Puntuacion);
        tv.setText("Puntuacion:" + this.puntuacion);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public void respuestaIncorrecta(View view) throws InterruptedException {

        MediaPlayer mpfail = MediaPlayer.create(this, R.raw.fail);
        mpfail.start();
        Thread.sleep(1000);
        Toast.makeText(this, "Respuesta incorrecta ", Toast.LENGTH_LONG).show();
        if(puntuacion<=2){
          puntuacion=0;
        }else {
            puntuacion -= 2;
        }
        Intent intent = new Intent(this, MainActivityFifthQuestion.class);
        intent.putExtra("puntuacion", getPuntuacion());
        startActivity(intent);
        finish();
    }

    public void respuestaCorrecta(View view) throws InterruptedException {
        MediaPlayer mpcorrect = MediaPlayer.create(this, R.raw.correct);
        mpcorrect.start();
        Thread.sleep(1000);
        puntuacion += 3;
        Toast.makeText(this, "Respuesta correcta", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivityFifthQuestion.class);
        intent.putExtra("puntuacion", getPuntuacion());
        startActivity(intent);
        finish();
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