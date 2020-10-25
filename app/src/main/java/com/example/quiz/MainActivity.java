package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
        int puntuacion = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Respuesta(View view){
            RadioButton idR = (RadioButton) findViewById(R.id.RespuestaCorrecta1);
            if(idR.isChecked()) {
                puntuacion += 3;
                Toast.makeText(this,"Respuesta correcta",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"Respuesta incorrecta ",Toast.LENGTH_LONG).show();
                puntuacion-=2;
        }
        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra("puntuacion",getPuntuacion());
            startActivity(intent);


    }
    public int getPuntuacion(){
        return puntuacion;
    }
}