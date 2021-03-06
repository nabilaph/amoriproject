package com.example.amoriproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.amoriproject.utils.DBHelper;

import java.text.DateFormat;
import java.util.Date;

public class NewReview extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // define variables
    EditText productName;
    EditText reviewDet;

    Button cancelReview;
    Button postReview;

    Spinner category;

    SharedPreferences sp;
    DBHelper dbHelper;

    // define the name of shared preferences and key
    String SP_NAME = "mypref";
    String KEY_UNAME = "username";
    String KEY_PASS = "password";

    //define variables
    String name, categorySelected, currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_review);

        //initiate DBHelper class
        dbHelper = new DBHelper(this);

        //get shared preferences
        sp = getSharedPreferences(SP_NAME, MODE_PRIVATE);

        //check availability of sp
        name = sp.getString(KEY_UNAME, null);

        //get current date
        currentDate = DateFormat.getDateInstance().format(new Date());

        // find components by id according to the defined variable
        productName = findViewById(R.id.productName);
        reviewDet = findViewById(R.id.reviewDet);

        cancelReview = findViewById(R.id.btn_cancelRev);
        postReview = findViewById(R.id.btn_postRev);

        category = findViewById(R.id.productCategory);

        //set adapter for category spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.productCategory, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);
        category.setOnItemSelectedListener(this);

        //set on click listener for cancel button
        cancelReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //set on click listener for post button
        postReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReview(productName.getText().toString(),
                        categorySelected,
                        reviewDet.getText().toString(),
                        name,
                        currentDate );


            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        categorySelected = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addReview(String productName, String productCategory, String reviewDetail, String username, String reviewDate){
        ContentValues values = new ContentValues();

        if(categorySelected.equals("Select Product Category")){
            //alert dialog for select the product category
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_warning)
                    .setMessage("Please select the category of your product")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .show();
        } else{
            //put values from DBHelper class table review for review details
            values.put(DBHelper.row_namaProduk, productName);
            values.put(DBHelper.row_category, productCategory);
            values.put(DBHelper.row_isiReview, reviewDetail);
            values.put(DBHelper.row_username, username);
            values.put(DBHelper.row_tanggal, reviewDate);
            dbHelper.insertReview(values);

            // make toast for display a text that review succesfully posted
            Toast.makeText(NewReview.this, "Review Posted", Toast.LENGTH_SHORT).show();
            //after review has posted, it will go back to the page before
            finish();
        }
    }
}