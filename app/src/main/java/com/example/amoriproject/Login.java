package com.example.amoriproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.amoriproject.utils.DBHelper;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    // define variables
    Button register, login;
    TextInputLayout username, pass;
    DBHelper dbHelper;
    SharedPreferences sp;

    // define the name of shared preferences and key
    String SP_NAME = "mypref";
    String KEY_UNAME = "username";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // find components by id according to the defined variable
        username = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        register = findViewById(R.id.btn_regis);
        login = findViewById(R.id.btn_login);

        //initiate DBHelper class
        dbHelper = new DBHelper(this);

        //get shared preferences
        sp = getSharedPreferences(SP_NAME, MODE_PRIVATE);

        //check availability of sp
        String uname = sp.getString(KEY_UNAME, null);

        if (uname!= null){
            //change page if already login
            startActivity(new Intent(Login.this, Dashboard.class));
        }

        //set onclick register button which will display on register page
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });


        //set onclick login button which will display on dashboard page
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user =  username.getEditText().getText().toString().trim();
                String password =  pass.getEditText().getText().toString().trim();

                // login method
                if(user.equals("") || password.equals("")){
                    Toast.makeText(Login.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                }else{
                    boolean res = dbHelper.checkUser(user, password);
                    if (res) {
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString(KEY_UNAME, user);
                        editor.commit();

                        Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT) .show();
                        startActivity(new Intent(Login.this, Dashboard.class));
                    } else {
                        Toast.makeText(Login.this, "Login Failed, please recheck your username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    @Override
    public void onBackPressed() {

        //set alert dialog for user who click back button on the phone
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent i = new Intent(Intent.ACTION_MAIN);
                i.addCategory(Intent.CATEGORY_HOME);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}