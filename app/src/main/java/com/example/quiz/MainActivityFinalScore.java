package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

public class MainActivityFinalScore extends AppCompatActivity {

    private int puntuacion;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_final_score);

        Bundle datos = this.getIntent().getExtras();
        puntuacion = datos.getInt("puntuacion");
        TextView tv = (TextView) findViewById(R.id.Puntuacion);
        tv.setText("Puntuación: " + this.puntuacion);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
            Intent intent = new Intent(this, ManualFinal.class);
            startActivity(intent);
        }
        return true;
    }
}