package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivityFirstQuestion extends AppCompatActivity {
    private int puntuacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_first_question);
    }
    public void Respuesta(View view){
        RadioButton idR = (RadioButton) findViewById(R.id.RespuestaCorrecta1);
        RadioButton idR2 = (RadioButton) findViewById(R.id.Respuestaincorrecta1);
        RadioButton idR3 = (RadioButton) findViewById(R.id.Respuestaincorrecta2);
        RadioButton idR4 = (RadioButton) findViewById(R.id.Respuestaincorrecta3);
        if(idR.isChecked()) {
            puntuacion += 3;
            Toast.makeText(this,"Respuesta correcta",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Activity2.class);
            intent.putExtra("puntuacion",getPuntuacion());
            startActivity(intent);
            finish();
        }else if(!idR.isChecked() && !idR2.isChecked() && !idR4.isChecked() & !idR3.isChecked()){
            Toast.makeText(this, "Marque una respuesta", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Respuesta incorrecta ",Toast.LENGTH_LONG).show();
            if(puntuacion<=2){
                puntuacion=0;
                Intent intent = new Intent(this, Activity2.class);
                intent.putExtra("puntuacion",getPuntuacion());
                startActivity(intent);
                finish();
            }else {
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
}
