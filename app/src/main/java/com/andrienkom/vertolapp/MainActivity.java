package com.andrienkom.vertolapp;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


import com.andrienkom.vertolapp.mvvm.fragments.FragmentEvents;
import com.andrienkom.vertolapp.mvvm.fragments.FragmentNews;
import com.andrienkom.vertolapp.mvvm.fragments.FragmentOther;
import com.andrienkom.vertolapp.mvvm.fragments.FragmentReadFsk;
import com.andrienkom.vertolapp.mvvm.fragments.FragmentSold;

public class MainActivity extends AppCompatActivity {

    private TextView mTitleTV;
    private View mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);

        mTitleTV = findViewById(R.id.ac_main_label);
        mToolbar = findViewById(R.id.ac_main_custom_toolbar);

        BottomNavigationView bnv = findViewById(R.id.bottom_navigation);
        bnv.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.navigation_news:
                    setTitle("Новости");
                    hideBar(false);
                    addFragment(FragmentNews.newInstance());
                    break;
                case R.id.navigation_sold:
                    setTitle("Скидки");
                    hideBar(false);
                    addFragment(FragmentSold.newInstance());
                    break;
                case R.id.navigation_events:
                    setTitle("События");
                    hideBar(false);
                    addFragment(FragmentEvents.newInstance());
                    break;
                case R.id.navigation_newspaper:
                    break;
                case R.id.navigation_others:
                    setTitle("Другое");
                    hideBar(true);
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

    public void setTitle(String title) {
        mTitleTV.setText(title);
    }

    public void hideBar(boolean hide) {
        if (hide) mToolbar.setVisibility(View.GONE);
        else mToolbar.setVisibility(View.VISIBLE);
    }

}
