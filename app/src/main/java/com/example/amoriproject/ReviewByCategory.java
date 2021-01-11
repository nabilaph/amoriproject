package com.example.amoriproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amoriproject.Adapter.RVAdapter;
import com.example.amoriproject.utils.DBHelper;

import java.util.ArrayList;

public class ReviewByCategory extends AppCompatActivity {

    RecyclerView RV_reviewCat;
    RVAdapter adapter;
    TextView selectedCategory;

    DBHelper dbHelper;
    ArrayList<String> product_name, review_detail, username, review_date, product_category;

    SharedPreferences sp;

    String SP_NAME = "mypref";
    String KEY_UNAME = "username";
    String KEY_PASS = "password";
    String KEY_CATEGORY = "category";
    String selected_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_by_category);

        sp = getSharedPreferences(SP_NAME, MODE_PRIVATE);

        //check availability of sp
        String name = sp.getString(KEY_UNAME, null);
        String pass = sp.getString(KEY_PASS, null);
        String category = sp.getString(KEY_CATEGORY, null);

        RV_reviewCat = findViewById(R.id.rv_reviewCat);

        selectedCategory = findViewById(R.id.selectedCategory);
        selectedCategory.setText(category);

        dbHelper = new DBHelper(this);

        product_name = new ArrayList<>();
        review_detail = new ArrayList<>();
        username = new ArrayList<>();
        review_date = new ArrayList<>();
        product_category = new ArrayList<>();

        storeDataInArray(category);

        adapter = new RVAdapter(this,product_name, product_category, review_detail , review_date, username);
        RV_reviewCat.setAdapter(adapter);
        RV_reviewCat.setLayoutManager(new LinearLayoutManager(this));
    }

    public void storeDataInArray(String category){
        Cursor cr = dbHelper.getReview(category);
        if(cr.getCount() == 0){
            Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_SHORT).show();
        }else{
            while(cr.moveToNext()){
                product_name.add(cr.getString(1));
                product_category.add(cr.getString(2));
                review_detail.add(cr.getString(3));
                username.add(cr.getString(4));
                review_date.add(cr.getString(5));
            }
        }
    }
}