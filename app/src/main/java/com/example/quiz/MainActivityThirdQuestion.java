package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivityThirdQuestion extends AppCompatActivity {

    private int puntuacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_third_question);

        Bundle datos = this.getIntent().getExtras();
        puntuacion = datos.getInt("puntuacion");
        TextView tv = (TextView) findViewById(R.id.puntuacion3);
        tv.setText("Puntuacion:"+this.puntuacion);
    }

    public int getPuntuacion() {
        return puntuacion;
    }
}