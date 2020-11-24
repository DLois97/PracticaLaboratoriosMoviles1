package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.quiz.dataBase.AdminSQLiteOpenerHelper;

import java.text.DecimalFormat;

public class MainActivityFinalScore extends AppCompatActivity {

    private int puntuacion;
    private String name;
    private TextView nameShow;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_final_score);

        Bundle datos = this.getIntent().getExtras();
        puntuacion = datos.getInt("puntuacion");
        name = datos.getString("name");
        TextView tv = (TextView) findViewById(R.id.Puntuacion);
        nameShow = (TextView) findViewById(R.id.textNameFinal);
        nameShow.setText(name);
        tv.setText("Puntuación: " + this.puntuacion);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        AdminSQLiteOpenerHelper admin = new AdminSQLiteOpenerHelper(this,"gestion",null,1);
        SQLiteDatabase database = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        double newAverage;
        long games;

        Cursor fila = database.rawQuery("select games,average from statistics where user = \'"+name +"\'",null);
        if (fila.moveToFirst()){
            registro.put("user", name);
            games = fila.getLong(0)+1;
            registro.put("games", games);
            newAverage = getNewAverage(fila.getDouble(1),fila.getLong(0));
            registro.put("average", newAverage);
            database.update("statistics",registro,"user=\'"+name+"\'",null);

        } else {
            registro.put("user", name);
            games = 1;
            registro.put("games", games);
            newAverage = puntuacion;
            registro.put("average",newAverage);
            database.insert("statistics", null, registro);
        }
        TextView avgPoint = (TextView)findViewById(R.id.avgPoints);
        TextView totalGames = (TextView)findViewById(R.id.numberGames);
        avgPoint.setText(new DecimalFormat("#.##").format(newAverage));
        totalGames.setText(String.valueOf(games));

    }

    private double getNewAverage(double oldAverage, long games) {
        double newAverage = (oldAverage * games + puntuacion)/ (games + 1);
        return newAverage;
    }

    public void retry(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("puntuacion",getPuntuacion());
        intent.putExtra("name",name);
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