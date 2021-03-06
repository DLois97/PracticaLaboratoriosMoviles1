package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

public class Activity2 extends AppCompatActivity {

    private int puntuacion;
    private ListView lv1;
    private String name;
    private String respuestas[] = {"X-Men","Los odiosos 8","Peter Pan","Gran torino"};
    private boolean soluciones[] = {true,false,false,false};
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Bundle datos = this.getIntent().getExtras();
        puntuacion = datos.getInt("puntuacion");
        name = datos.getString("name");
        TextView tv = (TextView) findViewById(R.id.Puntuacion);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final MediaPlayer mpfail = MediaPlayer.create(this, R.raw.fail);
        final MediaPlayer mpcorrect = MediaPlayer.create(this, R.raw.correct);

        tv.setText("Puntuacion:"+this.puntuacion);
        lv1 = (ListView) findViewById(R.id.lv1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,respuestas);
        lv1.setAdapter(adapter);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                if (soluciones[i]){
                    mpcorrect.start();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    puntuacion += 3;
                    Toast.makeText(adapterView.getContext(),"Respuesta correcta",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(adapterView.getContext(), MainActivityThirdQuestion.class);
                    intent.putExtra("puntuacion",getPuntuacion());
                    intent.putExtra("name",name);
                    startActivity(intent);
                    finish();
                } else {
                    if(puntuacion<=2){
                        puntuacion=0;
                    }else {
                        puntuacion -= 2;
                    }
                    mpfail.start();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(adapterView.getContext(),"Respuesta incorrecta ",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(adapterView.getContext(), MainActivityThirdQuestion.class);
                    intent.putExtra("puntuacion",getPuntuacion());
                    intent.putExtra("name",name);
                    startActivity(intent);
                    finish();
                }
            }
        });
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