package com.andrienkom.vertolapp;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.andrienkom.vertolapp.mvvm.fragments.FragmentChooseMonth;
import com.andrienkom.vertolapp.mvvm.fragments.FragmentNews;
import com.andrienkom.vertolapp.mvvm.fragments.FragmentNewspaper;
import com.andrienkom.vertolapp.mvvm.fragments.FragmentOther;
import com.andrienkom.vertolapp.mvvm.fragments.FragmentService;

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

                    addFragment(FragmentService.newInstance());
                    break;
                case R.id.navigation_events:

                    addFragment(FragmentChooseMonth.newInstance());
                    break;
                case R.id.navigation_newspaper:

                    addFragment(FragmentNewspaper.newInstance());
                    break;
                case R.id.navigation_others:

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

    public void showError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }


}
