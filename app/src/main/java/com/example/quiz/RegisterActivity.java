package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import com.example.quiz.dataBase.AdminSQLiteOpenerHelper;

import java.util.UUID;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameRegister;
    private EditText passwordRegister;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nameRegister = (EditText) findViewById(R.id.registerUserName);
        passwordRegister = (EditText) findViewById(R.id.passText);
    }


    public void registerUser(View view){
        AdminSQLiteOpenerHelper admin = new AdminSQLiteOpenerHelper(this,"gestion",null,1);

        SQLiteDatabase database = admin.getWritableDatabase();

        if ((!nameRegister.getText().toString().equals("") || nameRegister.getText().toString() != null) && (!passwordRegister.getText().toString().equals("") || passwordRegister.getText().toString() != null)){

            Cursor fila = database.rawQuery("select name from user where name = \'"+nameRegister.getText().toString() +"\'",null);
            if (fila.moveToFirst()){
                Toast.makeText(this,"El usuario ya existe",Toast.LENGTH_LONG).show();
            } else {
                ContentValues registro = new ContentValues();
                registro.put("name", nameRegister.getText().toString());
                registro.put("password", passwordRegister.getText().toString());
                database.insert("user", null, registro);

                database.close();

                nameRegister.setText("");
                passwordRegister.setText("");

                Toast.makeText(this, "Registro completado correctamente", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }
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
            Intent intent = new Intent(this, ManualRegistro.class);
            startActivity(intent);
        }
        return true;
    }
}