package com.andrienkom.vertolapp.mvvm.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.andrienkom.vertolapp.R;


public class FragmentChooseMonth extends Fragment {

    private View mToolbar;
    private TableLayout mTableLayout;


    public static FragmentChooseMonth newInstance(){
        return new FragmentChooseMonth();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr__events_choose_month,container,false);

        mToolbar = view.findViewById(R.id.fr_events_choose_month_custom_toolbar);
        mTableLayout = view.findViewById(R.id.month_table);



        return view;
    }
}
