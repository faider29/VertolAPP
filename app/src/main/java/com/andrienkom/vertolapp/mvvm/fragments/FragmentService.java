package com.andrienkom.vertolapp.mvvm.fragments;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andrienkom.vertolapp.MainActivity;
import com.andrienkom.vertolapp.R;
import com.andrienkom.vertolapp.entities.Sold;
import com.andrienkom.vertolapp.mvvm.viewModels.SoldViewModel;
import com.andrienkom.vertolapp.utility.adapters.SoldAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FragmentService extends Fragment {

    private RecyclerView mRecyclerView;
    private SoldAdapter mAdapter;
    private List<Sold> mSoldList = new ArrayList<>();




    private View mToolbar;
    private TextView mLabel;

    private SoldViewModel mViewModel;

    private static String TAG = FragmentService.class.getSimpleName();


    public static FragmentService newInstance(){
        return new FragmentService();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_service,container,false);


        mToolbar = view.findViewById(R.id.fr_service_custom_toolbar);
        mLabel = view.findViewById(R.id.fr_service_label);

        mAdapter = new SoldAdapter(getContext(), mSoldList);
        mAdapter.setOnItemClickListener((position, sold) -> {
            ((MainActivity) getActivity()).addFragmentToBackStack(FragmentReadService.newInstance(sold));
        });
        mRecyclerView = view.findViewById(R.id.rv_service);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        observe();


        return view;
    }
    private void observe() {
        mViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(SoldViewModel.class);
        LiveData<List<Sold>> solds = mViewModel.getSold();
        solds.observe(getActivity(), soldList -> {
            mSoldList.clear();
            mSoldList.addAll(soldList);
            mAdapter.notifyDataSetChanged();
            Log.d(TAG, "observe: " + soldList.size());

        });

        LiveData<String> error = mViewModel.getError();
        error.observe(getActivity(), errorMessage -> {

        });

        
    }



}

