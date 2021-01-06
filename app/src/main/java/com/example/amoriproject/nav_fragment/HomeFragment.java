package com.example.amoriproject.nav_fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amoriproject.Adapter.SectionPagerAdapter;
import com.example.amoriproject.R;
import com.example.amoriproject.feed_fragment.MyReviewFragment;
import com.example.amoriproject.feed_fragment.PublicReviewFragment;
import com.google.android.material.tabs.TabLayout;


public class HomeFragment extends Fragment {

    public HomeFragment (){

    }

    public static HomeFragment getInstance(){
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }



}