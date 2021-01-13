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

import com.example.amoriproject.nav_fragment.FeedFragment;
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

    String id_review, categorySelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_review);

        sp = getSharedPreferences(SP_NAME, MODE_PRIVATE);

        //check availability of sp
        id_review = String.valueOf(sp.getInt(KEY_REVIEWID, 0));
//        Intent i = getIntent();
//        id_review = i.getStringExtra("rev_id");

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

        getReview(id_review);

        int p = selectSpinnerItembyValue(category, categorySelected);
        category.setSelection(p);

        cancelReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        updateReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String product_name = productName.getText().toString();
                String review_detail = reviewDet.getText().toString();
                updateReview(id_review, product_name, categorySelected, review_detail);
                finish();
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

    public void updateReview(String idReview, String productName, String productCategory, String reviewDetail) {

        if (productCategory.equals("Select Product Category")) {
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
            dbHelper.updateReview(idReview, productName, productCategory, reviewDetail);

            Toast.makeText(EditReview.this, "Review Updated", Toast.LENGTH_SHORT).show();
        }

    }


    public int selectSpinnerItembyValue(Spinner spn, String value){
        ArrayAdapter adapter = (ArrayAdapter) spn.getAdapter();
        for (int position = 0; position < adapter.getCount();position++){
            if(value.equals(adapter.getItemId(position))){
                return position;

            }
        }
        return 0;
    }

    public void getReview(String id){
        Cursor res = dbHelper.getReviewbyId(id);
        res.moveToNext();
        productName.setText(res.getString(1));
        reviewDet.setText(res.getString(3));
        categorySelected = res.getString(2);
    }

}