package com.example.amoriproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.amoriproject.nav_fragment.FeedFragment;
import com.example.amoriproject.nav_fragment.HomeFragment;
import com.example.amoriproject.nav_fragment.ProfileFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class Dashboard extends AppCompatActivity {

    private ChipNavigationBar chipnav;
    private Fragment fragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        chipnav = findViewById(R.id.chipnav);

        chipnav.setItemSelected(R.id.nav_home, true);
        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, new HomeFragment()).commit();

        chipnav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {

            @Override
            public void onItemSelected(int i) {
                switch(i){
                    case R.id.nav_feed:
                        fragment = new FeedFragment();
                        break;
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.nav_profile:
                        fragment = new ProfileFragment();
                        break;
                }

                if (fragment != null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, fragment).commit();

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, fragment).commit();
            }
        });
    }
}