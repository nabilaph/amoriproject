package com.example.amoriproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.amoriproject.utils.DBHelper;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    Button register, login;
    TextInputLayout username, pass;
    DBHelper dbHelper;
    SharedPreferences sp;

    String SP_NAME = "mypref";
    String KEY_UNAME = "username";
    String KEY_PASS = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        register = findViewById(R.id.btn_regis);
        login = findViewById(R.id.btn_login);

        dbHelper = new DBHelper(this);

        sp = getSharedPreferences(SP_NAME, MODE_PRIVATE);

        //check availability of sp
        String name = sp.getString(KEY_UNAME, null);

        if (name!= null){
            startActivity(new Intent(Login.this, Dashboard.class));
        }



        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user =  username.getEditText().getText().toString().trim();
                String password =  pass.getEditText().getText().toString().trim();

                Boolean res = dbHelper.checkUser(user, password);
                if (res == true) {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString(KEY_UNAME, user);
                    editor.putString(KEY_PASS, password);
                    editor.commit();

                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT) .show();
                    startActivity(new Intent(Login.this, Dashboard.class));
                } else {
                    Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}