package com.andrienkom.vertolapp.mvvm.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andrienkom.vertolapp.R;

public class FragmentNewspaper extends Fragment {

    private View mToolbar;
    private TextView mText;

    public static FragmentNewspaper newInstance(){
        return new FragmentNewspaper();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_newspaper,container,false);

        mToolbar = view.findViewById(R.id.fr_newspaper_custom_toolbar);
        mText = view.findViewById(R.id.tv_newspaper_working);

        return view;
    }





}
