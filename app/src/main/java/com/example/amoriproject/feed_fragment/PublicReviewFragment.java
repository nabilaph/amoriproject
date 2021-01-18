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
import com.example.amoriproject.R;
import com.example.amoriproject.nav_fragment.ProfileFragment;
import com.example.amoriproject.utils.DBHelper;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class PublicReviewFragment extends Fragment {

    //define variables
    RecyclerView RV_review;
    RVAdapter adapter;
    View myFrag;
    SwipeRefreshLayout swipeRefresh;

    DBHelper dbHelper;
    ArrayList<String> product_name, review_detail, username, review_date, product_category;

    public PublicReviewFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        swipeRefresh = myFrag.findViewById(R.id.swipeRefresh_pr);
        //swipe refresh on click listener
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
             getFragmentManager().beginTransaction().detach(PublicReviewFragment.this).attach(PublicReviewFragment.this).commit();


                swipeRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFrag = inflater.inflate(R.layout.fragment_public_review, container, false);

        // find components by id according to the defined variable
        RV_review = myFrag.findViewById(R.id.rv_review);

        //initiate DBHelper class
        dbHelper = new DBHelper(getContext());

        //make array list for each content
        product_name = new ArrayList<>();
        review_detail = new ArrayList<>();
        username = new ArrayList<>();
        review_date = new ArrayList<>();
        product_category = new ArrayList<>();

        storeDataInArray();

        //set adapter
        adapter = new RVAdapter(getContext(),product_name, product_category, review_detail , review_date, username);
        RV_review.setAdapter(adapter);
        RV_review.setLayoutManager(new LinearLayoutManager(getContext()));

        return myFrag;
    }

    public void storeDataInArray(){
        //get all review from database
        Cursor cr = dbHelper.fetchAllReview();
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