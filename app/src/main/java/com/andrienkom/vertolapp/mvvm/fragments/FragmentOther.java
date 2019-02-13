package com.andrienkom.vertolapp.mvvm.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andrienkom.vertolapp.MainActivity;
import com.andrienkom.vertolapp.R;



public class FragmentOther extends Fragment {


    private TextView mText;


    public static FragmentOther newInstance(){
        return new FragmentOther();
    }





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.other_mc,container,false);



        mText = view.findViewById(R.id.item_fck);
        mText.setOnClickListener(v -> {
            ((MainActivity) getActivity()).addFragment(FragmentReadFsk.newInstance());
        });


        mText = view.findViewById(R.id.item_sdk);
        mText.setOnClickListener(v -> {
            ((MainActivity) getActivity()).addFragment(FragmentReadSdk.newInstance());
        });

        mText = view.findViewById(R.id.item_youth);
        mText.setOnClickListener(v -> {
            ((MainActivity) getActivity()).addFragment(FragmentReadYouth.newInstance());
        });
        return view;
    }


}
