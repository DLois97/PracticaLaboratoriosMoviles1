package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quiz.dataBase.AdminSQLiteOpenerHelper;

public class LoginActivity extends AppCompatActivity {

    private EditText nameLogin;
    private EditText passwordLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        nameLogin = (EditText) findViewById(R.id.nameUser);
        passwordLogin = (EditText) findViewById(R.id.passUsuario);
    }

    public void login(View view){
        AdminSQLiteOpenerHelper admin = new AdminSQLiteOpenerHelper(this,"gestion",null,1);

        SQLiteDatabase database = admin.getWritableDatabase();

        if ((!nameLogin.getText().toString().equals("") || nameLogin.getText().toString() != null) && (!passwordLogin.getText().toString().equals("") || passwordLogin.getText().toString() != null)){
            Cursor fila = database.rawQuery("select name,password from user where name = \'"+nameLogin.getText().toString() +"\'",null);
            if (fila.moveToFirst()){
                if (passwordLogin.getText().toString().equals(fila.getString(1))){
                    nameLogin.setText("");
                    passwordLogin.setText("");
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "contraseña incorrecta", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(this, "Usuario incorrecto", Toast.LENGTH_LONG).show();

            }
        }
    }


    public void startRegister(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}