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

public class FeedFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager vp;

    View myFragment;

    public FeedFragment (){

    }

    public static FeedFragment getInstance(){
        return new FeedFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragment = inflater.inflate(R.layout.fragment_feed, container, false);

        vp = myFragment.findViewById(R.id.viewPager);
        tabLayout = myFragment.findViewById(R.id.tabLayout);

        return myFragment;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setUpViewPager(vp);
        tabLayout.setupWithViewPager(vp);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setUpViewPager(ViewPager viewPager) {

        SectionPagerAdapter adapter = new SectionPagerAdapter(getChildFragmentManager());

        adapter.addFragment(new PublicReviewFragment(), "Public Review");
        adapter.addFragment(new MyReviewFragment(), "My Review");
    }

}