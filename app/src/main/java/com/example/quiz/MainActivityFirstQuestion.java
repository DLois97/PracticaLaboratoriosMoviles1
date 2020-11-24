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
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

public class MainActivityFirstQuestion extends AppCompatActivity {
    private int puntuacion;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_first_question);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
    public void Respuesta(View view) throws InterruptedException {
        RadioButton idR = (RadioButton) findViewById(R.id.RespuestaCorrecta1);
        RadioButton idR2 = (RadioButton) findViewById(R.id.Respuestaincorrecta1);
        RadioButton idR3 = (RadioButton) findViewById(R.id.Respuestaincorrecta2);
        RadioButton idR4 = (RadioButton) findViewById(R.id.Respuestaincorrecta3);
        MediaPlayer mpfail = MediaPlayer.create(this, R.raw.fail);
        MediaPlayer mpcorrect = MediaPlayer.create(this, R.raw.correct);
        if(idR.isChecked()) {
            mpcorrect.start();
            Thread.sleep(1000);
            puntuacion += 3;
            Toast.makeText(this,"Respuesta correcta",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Activity2.class);
            intent.putExtra("puntuacion",getPuntuacion());
            startActivity(intent);
            finish();
        }else if(!idR.isChecked() && !idR2.isChecked() && !idR4.isChecked() & !idR3.isChecked()){
            mpfail.start();
            Thread.sleep(1000);
            Toast.makeText(this, "Marque una respuesta", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Respuesta incorrecta ",Toast.LENGTH_LONG).show();
            if(puntuacion<=2){
                mpfail.start();
                Thread.sleep(1000);
                puntuacion=0;
                Intent intent = new Intent(this, Activity2.class);
                intent.putExtra("puntuacion",getPuntuacion());
                startActivity(intent);
                finish();
            }else {
                mpfail.start();
                Thread.sleep(1000);
                puntuacion -= 2;
                Intent intent = new Intent(this, Activity2.class);
                intent.putExtra("puntuacion",getPuntuacion());
                startActivity(intent);
                finish();
            }
        }



    }
    public int getPuntuacion(){
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
