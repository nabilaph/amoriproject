package com.example.amoriproject.Adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.amoriproject.feed_fragment.MyReviewFragment;
import com.example.amoriproject.feed_fragment.PublicReviewFragment;

import java.util.ArrayList;
import java.util.List;

public class SectionPagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public SectionPagerAdapter( FragmentManager fm, int numOfTabs) {

        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new PublicReviewFragment();
            case 1:
                return new MyReviewFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {

        return numOfTabs;
    }

}
