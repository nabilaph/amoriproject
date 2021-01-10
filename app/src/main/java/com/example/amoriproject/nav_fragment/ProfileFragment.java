package com.example.amoriproject.nav_fragment;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amoriproject.Login;
import com.example.amoriproject.R;
import com.example.amoriproject.Register;
import com.example.amoriproject.utils.DBHelper;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {

    TextInputLayout fullname, username, password, email;
    TextView bigFullname, bigUsername;
    Button updateProfile, logOut;

    Context context;
    View myFragment;
    DBHelper db;

    SharedPreferences sp;

    String SP_NAME = "mypref";
    String KEY_UNAME = "username";
    String KEY_PASS = "password";

    ArrayList<String> userData = new ArrayList();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragment = inflater.inflate(R.layout.fragment_profile, container, false);

        fullname = myFragment.findViewById(R.id.fullname);
        username = myFragment.findViewById(R.id.username);
        email = myFragment.findViewById(R.id.email);
        password = myFragment.findViewById(R.id.password);
        bigFullname = myFragment.findViewById(R.id.bigFullname);
        bigUsername = myFragment.findViewById(R.id.bigUsername);

        sp = getContext().getSharedPreferences(SP_NAME, MODE_PRIVATE);

        //check availability of sp
        final String name = sp.getString(KEY_UNAME, null);
        String pass = sp.getString(KEY_PASS, null);

        displayProfile(name);

        updateProfile = myFragment.findViewById(R.id.btn_update);
        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile(name, fullname.getEditText().getText().toString(),
                        email.getEditText().getText().toString(),
                        password.getEditText().getText().toString());

            }
        });

        logOut = myFragment.findViewById(R.id.btn_logout);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();

                Toast.makeText(getContext(), "Logged Out", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, Login.class);
                startActivity(i);
            }
        });

        return myFragment;
    }

    public void displayProfile(String Username){
        db = new DBHelper(context);
        Cursor data = db.fetchAllProfileData(Username);
        while(data.moveToNext()){
            userData.add(data.getString(0));
            userData.add(data.getString(1));
            userData.add(data.getString(2));
            userData.add(data.getString(3));
            userData.add(data.getString(4));
        }

        fullname.getEditText().setText(userData.get(1));
        email.getEditText().setText(userData.get(2));
        username.getEditText().setText(userData.get(3));
        password.getEditText().setText(userData.get(4));
        bigFullname.setText(userData.get(1));
        bigUsername.setText(userData.get(3));
    }

    public void updateProfile(String usernameSP, String fullname, String email, String password){
        db = new DBHelper(context);
        int idUser = db.getIdUser(usernameSP);

//        ContentValues cv = new ContentValues();
//        cv.put("_idUser", idUser);
//        cv.put("FullName", fullname);
//        cv.put("Email", email);
//        cv.put("Username", username);
//        cv.put("Password", password);
//        db.updateProfileData(cv, String.valueOf(idUser));

        db.updateProfileData(fullname, email, password, String.valueOf(idUser));

        Toast.makeText(getContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
    }

}