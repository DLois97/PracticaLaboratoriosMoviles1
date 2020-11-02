package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {

    private int puntuacion;
    private ListView lv1;
    private String respuestas[] = {"X-Men","Los odiosos 8","Peter Pan","Gran torino"};
    private boolean soluciones[] = {true,false,false,false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Bundle datos = this.getIntent().getExtras();
        puntuacion = datos.getInt("puntuacion");
        TextView tv = (TextView) findViewById(R.id.Puntuacion);

        tv.setText("Puntuacion:"+this.puntuacion);
        lv1 = (ListView) findViewById(R.id.lv1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,respuestas);
        lv1.setAdapter(adapter);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                if (soluciones[i]){
                    puntuacion += 3;
                    Toast.makeText(adapterView.getContext(),"Respuesta correcta",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(adapterView.getContext(), MainActivityThirdQuestion.class);
                    intent.putExtra("puntuacion",getPuntuacion());
                    startActivity(intent);
                    finish();
                } else {
                    if(puntuacion<=2){
                        puntuacion=0;
                    }else {
                        puntuacion -= 2;
                    }
                    Toast.makeText(adapterView.getContext(),"Respuesta incorrecta ",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(adapterView.getContext(), MainActivityThirdQuestion.class);
                    intent.putExtra("puntuacion",getPuntuacion());
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    public int getPuntuacion() {
        return puntuacion;
    }
}