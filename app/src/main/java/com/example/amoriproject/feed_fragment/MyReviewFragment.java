package com.example.amoriproject.feed_fragment;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.amoriproject.Adapter.RVAdapter;
import com.example.amoriproject.Adapter.RVAdapterMyRev;
import com.example.amoriproject.R;
import com.example.amoriproject.utils.DBHelper;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class MyReviewFragment extends Fragment {

    RecyclerView RV_myreview;
    RVAdapterMyRev adapter;
    View myFrag;

    DBHelper dbHelper;
    ArrayList<String> product_name, review_detail, username, review_date, product_category;

    SharedPreferences sp;

    String SP_NAME = "mypref";
    String KEY_UNAME = "username";
    String KEY_PASS = "password";
    String KEY_IDREV = "_idReview";

    public MyReviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFrag = inflater.inflate(R.layout.fragment_my_review, container, false);

        sp = getContext().getSharedPreferences(SP_NAME, MODE_PRIVATE);

        //check availability of sp
        String name = sp.getString(KEY_UNAME, null);
        String pass = sp.getString(KEY_PASS, null);

        RV_myreview = myFrag.findViewById(R.id.rv_review_my);

        dbHelper = new DBHelper(getContext());
        product_name = new ArrayList<>();
        review_detail = new ArrayList<>();
        username = new ArrayList<>();
        review_date = new ArrayList<>();
        product_category = new ArrayList<>();

        storeDataInArray(name);

        adapter = new RVAdapterMyRev(getContext(),product_name, product_category, review_detail , review_date, username);
        RV_myreview.setAdapter(adapter);
        RV_myreview.setLayoutManager(new LinearLayoutManager(getContext()));

        return myFrag;
    }

    //harus ada method fetchReviewByUsername
    public void storeDataInArray(String user_name){
        Cursor cr = dbHelper.fetchMyReview(user_name);
        if(cr.getCount() == 0){
            Toast.makeText(getContext(), "No data", Toast.LENGTH_SHORT).show();
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

//    public void deleteMyReview(String productName, String productCategory, String reviewDet, String username, String tanggal){
//        int id = dbHelper.getIdRev(productName, productCategory, reviewDet, username, tanggal);
//        boolean res = dbHelper.deleteReview(String.valueOf(id));
//
//        if (res){
//            Toast.makeText(getContext(), "Review Deleted", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    public void updateMyReview(String productName, String productCategory, String reviewDet, String username, String tanggal){
//        int id = dbHelper.getIdRev(productName, productCategory, reviewDet, username, tanggal);
//        boolean res = dbHelper.updateReview(productName, productCategory, reviewDet, username, tanggal, String.valueOf(id));
//
//        if (res){
//            Toast.makeText(getContext(), "Review Updated", Toast.LENGTH_SHORT).show();
//        }
//    }
}