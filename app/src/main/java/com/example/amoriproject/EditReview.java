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

public class EditReview extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText productName;
    EditText reviewDet;

    Button cancelReview;
    Button updateReview;

    Spinner category;

    DBHelper dbHelper;
    SharedPreferences sp;

    String SP_NAME = "mypref";
    String KEY_UNAME = "username";
    String KEY_PASS = "password";
    String KEY_REVIEWID = "idRev";

    String id_review, pass, categorySelected, currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_review);

        sp = getSharedPreferences(SP_NAME, MODE_PRIVATE);

        //check availability of sp
        id_review = sp.getString(KEY_REVIEWID, null);

        productName = findViewById(R.id.productName);
        reviewDet = findViewById(R.id.reviewDet);

        cancelReview = findViewById(R.id.btn_cancelRev);
        updateReview = findViewById(R.id.btn_updateRev);

        category = findViewById(R.id.productCategory);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.productCategory, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);
        category.setOnItemSelectedListener(this);

        dbHelper = new DBHelper(this);

        cancelReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditReview.this, Dashboard.class);
                startActivity(intent);
            }
        });

        updateReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String product_name = productName.getText().toString();
                String review_detail = reviewDet.getText().toString();
                updateReview(id_review, product_name, categorySelected, review_detail);
                Intent intent = new Intent(EditReview.this, Dashboard.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String categorySelected = parent.getItemAtPosition(position).toString();
        if (categorySelected.equals("Select Product Category")) {
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_warning)
                    .setMessage("Please select the category of your product")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .show();
        } else {

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void updateReview(String idReview, String productName, String productCategory, String reviewDetail) {
//        ContentValues values = new ContentValues();

        if (categorySelected.equals("Select Product Category")) {
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
        } else {
//            values.put(DBHelper.row_namaProduk, productName);
//            values.put(DBHelper.row_category, productCategory);
//            values.put(DBHelper.row_isiReview, reviewDetail);
//            values.put(DBHelper.row_username, username);
//            values.put(DBHelper.row_tanggal, reviewDate);
            dbHelper.updateReview(idReview, productName, productCategory, reviewDetail);

            Toast.makeText(EditReview.this, "Review Updated", Toast.LENGTH_SHORT).show();
        }

    }

}