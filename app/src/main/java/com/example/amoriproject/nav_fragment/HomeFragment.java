package com.example.amoriproject.nav_fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.amoriproject.Adapter.SectionPagerAdapter;
import com.example.amoriproject.R;
import com.example.amoriproject.feed_fragment.MyReviewFragment;
import com.example.amoriproject.feed_fragment.PublicReviewFragment;
import com.example.amoriproject.utils.DBHelper;
import com.google.android.material.tabs.TabLayout;

import static android.content.Context.MODE_PRIVATE;


public class HomeFragment extends Fragment {

    Button Makeup, Skincare, bodyCare, beautyTools;

    Context context;
    View myFragment;
    DBHelper db;

    SharedPreferences sp;

    String SP_NAME = "mypref";
    String KEY_UNAME = "username";
    String KEY_PASS = "password";
    String KEY_CATEGORY = "category";

    public HomeFragment (){

    }

    public static HomeFragment getInstance(){
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragment = inflater.inflate(R.layout.fragment_home, container, false);

        Makeup = myFragment.findViewById(R.id.Makeup);
        Skincare = myFragment.findViewById(R.id.Skincare);
        bodyCare = myFragment.findViewById(R.id.bodyCare);
        beautyTools = myFragment.findViewById(R.id.beautyTools);

        sp = getContext().getSharedPreferences(SP_NAME, MODE_PRIVATE);

        Makeup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sp.edit();
                String Category = "Make Up";
                editor.putString(KEY_CATEGORY, Category );
                editor.commit();

            }
        });

        Skincare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sp.edit();
                String Category = "Skin Care";
                editor.putString(KEY_CATEGORY, Category );
                editor.commit();

            }
        });

        bodyCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sp.edit();
                String Category = "Body Care";
                editor.putString(KEY_CATEGORY, Category );
                editor.commit();

            }
        });

        beautyTools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sp.edit();
                String Category = "Beauty Tools";
                editor.putString(KEY_CATEGORY, Category );
                editor.commit();

            }
        });
        return myFragment;
    }



}