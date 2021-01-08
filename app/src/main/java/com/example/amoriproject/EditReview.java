package com.example.amoriproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class EditReview extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText productName;
    EditText reviewDet;

    Button cancelReview;
    Button updateReview;

    Spinner category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_review);

        productName = findViewById(R.id.productName);
        reviewDet = findViewById(R.id.reviewDet);

        cancelReview = findViewById(R.id.btn_cancelRev);
        updateReview = findViewById(R.id.btn_updateRev);

        category = findViewById(R.id.productCategory);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.productCategory, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);
        category.setOnItemSelectedListener(this);

        cancelReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditReview.this, Dashboard.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String categorySelected = parent.getItemAtPosition(position).toString();
        if(categorySelected.equals("Select Product Category")){
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_warning)
                    .setMessage("Please select the category of your product")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .show();
        } else{

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}