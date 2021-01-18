package com.example.amoriproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.amoriproject.feed_fragment.MyReviewFragment;
import com.example.amoriproject.nav_fragment.FeedFragment;
import com.example.amoriproject.utils.DBHelper;

public class EditReview extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // define variables
    EditText productName;
    EditText reviewDet;

    Button cancelReview;
    Button updateReview;

    Spinner category;

    DBHelper dbHelper;
    SharedPreferences sp;

    // define the name of shared preferences and key
    String SP_NAME = "mypref";
    String KEY_REVIEWID = "idRev";

    //define variables
    String id_review, categorySelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_review);

        //get shared preferences
        sp = getSharedPreferences(SP_NAME, MODE_PRIVATE);

        //check availability of sp
        id_review = String.valueOf(sp.getInt(KEY_REVIEWID, 0));

        //initiate DBHelper class
        dbHelper = new DBHelper(this);

        // find components by id according to the defined variable
        productName = findViewById(R.id.productName);
        reviewDet = findViewById(R.id.reviewDet);

        cancelReview = findViewById(R.id.btn_cancelRev);
        updateReview = findViewById(R.id.btn_updateRev);

        category = findViewById(R.id.productCategory);

        categorySelected = getReview(id_review);

        //set adapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.productCategory, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);
        category.setOnItemSelectedListener(this);

        int p = adapter.getPosition(categorySelected);
        category.setSelection(p);

        //set on click listener for cancel button
        cancelReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //set on click listener for update button
        updateReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String product_name = productName.getText().toString();
                String review_detail = reviewDet.getText().toString();
                updateReview(id_review, product_name, categorySelected, review_detail);

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        categorySelected = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Please select the category", Toast.LENGTH_SHORT).show();
    }

    public void updateReview(String idReview, String productName, String productCategory, String reviewDetail) {

        if (productCategory.equals("Select Product Category")) {
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
        } else {
            //update values of the review to database
            dbHelper.updateReview(idReview, productName, productCategory, reviewDetail);

            // make toast for display a text that review succesfully updated
            Toast.makeText(EditReview.this, "Review Updated", Toast.LENGTH_SHORT).show();
            //after review has updated, it will go back to the page before
            finish();
        }

    }


    public String getReview(String id){
        //method to get content of table review based on id
        Cursor res = dbHelper.getReviewbyId(id);
        res.moveToNext();
        productName.setText(res.getString(1));
        reviewDet.setText(res.getString(3));
        String categorySelected = res.getString(2);

        return categorySelected;
    }

}