package com.andrienkom.vertolapp;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


import com.andrienkom.vertolapp.mvvm.fragments.FragmentEvents;
import com.andrienkom.vertolapp.mvvm.fragments.FragmentNews;
import com.andrienkom.vertolapp.mvvm.fragments.FragmentOther;
import com.andrienkom.vertolapp.mvvm.fragments.FragmentReadFsk;
import com.andrienkom.vertolapp.mvvm.fragments.FragmentSold;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);







        BottomNavigationView bnv = findViewById(R.id.bottom_navigation);
        bnv.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.navigation_news:
                    setTitle("Новости");
                    addFragment(FragmentNews.newInstance());
                    break;
                case R.id.navigation_sold:
                    setTitle("Скидки");
                    addFragment(FragmentSold.newInstance());
                    break;
                case R.id.navigation_events:
                    setTitle("События");
                    addFragment(FragmentEvents.newInstance());
                    break;
                case R.id.navigation_newspaper:
                    break;
                case R.id.navigation_others:
                    setTitle("Другое");
                    addFragment(FragmentOther.newInstance());
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





    public void addFragmentToBackStack(Fragment fragment) {
        String backStateName = fragment.getClass().getSimpleName();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragment)
                .addToBackStack(backStateName)
                .commit();
    }


}
