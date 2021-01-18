package com.example.amoriproject.feed_fragment;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.amoriproject.Adapter.RVAdapter;
import com.example.amoriproject.Adapter.RVAdapterMyRev;
import com.example.amoriproject.R;
import com.example.amoriproject.nav_fragment.ProfileFragment;
import com.example.amoriproject.utils.DBHelper;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class MyReviewFragment extends Fragment {

    //define variables
    RecyclerView RV_myreview;
    RVAdapterMyRev adapter;
    View myFrag;
    SwipeRefreshLayout swipeRefresh;

    DBHelper dbHelper;
    ArrayList<String> product_name, review_detail, username, review_date, product_category;

    SharedPreferences sp;

    // define the name of shared preferences and key
    String SP_NAME = "mypref";
    String KEY_UNAME = "username";
    String name;

    public MyReviewFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        swipeRefresh = myFrag.findViewById(R.id.swipeRefresh_mr);
        //swipe refresh on click listener
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getFragmentManager().beginTransaction().detach(MyReviewFragment.this).attach(MyReviewFragment.this).commit();
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFrag = inflater.inflate(R.layout.fragment_my_review, container, false);

        //get shared preferences
        sp = getContext().getSharedPreferences(SP_NAME, MODE_PRIVATE);

        //check availability of sp
        name = sp.getString(KEY_UNAME, null);

        // find components by id according to the defined variable
        RV_myreview = myFrag.findViewById(R.id.rv_review_my);

        //initiate DBHelper class
        dbHelper = new DBHelper(getContext());
        product_name = new ArrayList<>();
        review_detail = new ArrayList<>();
        username = new ArrayList<>();
        review_date = new ArrayList<>();
        product_category = new ArrayList<>();

        storeDataInArray(name);

        //set adapter for my review fragment
        adapter = new RVAdapterMyRev(getContext(),product_name, product_category, review_detail , review_date, username);
        RV_myreview.setAdapter(adapter);
        RV_myreview.setLayoutManager(new LinearLayoutManager(getContext()));

        return myFrag;
    }


    public void storeDataInArray(String user_name){
        //get data from database
        Cursor cr = dbHelper.fetchMyReview(user_name);
        if(cr.getCount() == 0){

            //make toast for tell user there is no data
            Toast.makeText(getContext(), "No data", Toast.LENGTH_SHORT).show();
        }else{
            while(cr.moveToNext()){
                //add data to array list
                product_name.add(cr.getString(1));
                product_category.add(cr.getString(2));
                review_detail.add(cr.getString(3));
                username.add(cr.getString(4));
                review_date.add(cr.getString(5));
            }
        }
    }

}