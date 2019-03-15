package com.andrienkom.vertolapp.mvvm.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andrienkom.vertolapp.R;

public class FragmentNewspaper extends Fragment {

    private View mToolbar;
    private RecyclerView mRVHorizontal;
    private RecyclerView mRVVertical;



    public static FragmentNewspaper newInstance(){
        return new FragmentNewspaper();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_newspaper,container,false);

        mToolbar = view.findViewById(R.id.fr_newspaper_custom_toolbar);
        mRVHorizontal = view.findViewById(R.id.rv_newspaper_issue);
        mRVVertical = view.findViewById(R.id.rv_newspaper_news);

        return view;
    }





}
