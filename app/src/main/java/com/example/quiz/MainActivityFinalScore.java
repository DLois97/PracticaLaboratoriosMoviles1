package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivityFinalScore extends AppCompatActivity {

    private int puntuacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_final_score);

        Bundle datos = this.getIntent().getExtras();
        puntuacion = datos.getInt("puntuacion");
        TextView tv = (TextView) findViewById(R.id.Puntuacion);
        tv.setText("Puntuación: " + this.puntuacion);
    }

    public void retry(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("puntuacion",getPuntuacion());
        startActivity(intent);
        finish();
    }

    public void quit(View view){
        System.exit(0);
    }

    public int getPuntuacion() {
        return puntuacion;
    }
}