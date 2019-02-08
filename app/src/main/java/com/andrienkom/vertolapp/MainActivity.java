package com.andrienkom.vertolapp;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;


import com.andrienkom.vertolapp.mvvm.fragments.FragmentEvents;
import com.andrienkom.vertolapp.mvvm.fragments.FragmentNews;
import com.andrienkom.vertolapp.mvvm.fragments.FragmentSold;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        BottomNavigationView bnv = findViewById(R.id.bottom_navigation);
        bnv.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.navigation_news:
                    addFragment(FragmentNews.newInstance());
                    break;
                case R.id.navigation_sold:
                    addFragment(FragmentSold.newInstance());
                    break;
                case R.id.navigation_events:
                    addFragment(FragmentEvents.newInstance());
                    break;
                case R.id.navigation_newspaper:
                    break;
                case R.id.navigation_others:
                    break;
            }
            return true;
        });

        if (getSupportFragmentManager().getFragments().size() == 0) {
            addFragment(FragmentNews.newInstance());
        }




    }
    public void addFragment(Fragment fragment) {
        getSupportFragmentManager().popBackStackImmediate(null,0);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }


}
