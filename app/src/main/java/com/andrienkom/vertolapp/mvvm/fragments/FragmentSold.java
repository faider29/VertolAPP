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
import android.widget.ImageView;
import android.widget.TextView;

import com.andrienkom.vertolapp.MainActivity;
import com.andrienkom.vertolapp.R;
import com.andrienkom.vertolapp.entities.Sold;
import com.andrienkom.vertolapp.mvvm.viewModels.SoldViewModel;
import com.andrienkom.vertolapp.utility.adapters.SoldAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FragmentSold extends Fragment {

    private RecyclerView mRecyclerView;
    private SoldAdapter mAdapter;
    private List<Sold> mSoldList = new ArrayList<>();




    private View mToolbar;
    private TextView mLabel;

    private SoldViewModel mViewModel;

    private static String TAG = FragmentSold.class.getSimpleName();


    public static FragmentSold newInstance(){
        return new FragmentSold();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_sold,container,false);


        mToolbar = view.findViewById(R.id.fr_sold_custom_toolbar);
        mLabel = view.findViewById(R.id.fr_sold_label);

        mAdapter = new SoldAdapter(getContext(), mSoldList);
        mAdapter.setOnItemClickListener((position, sold) -> {
            ((MainActivity) getActivity()).addFragmentToBackStack(FragmentReadSold.newInstance(sold));
        });
        mRecyclerView = view.findViewById(R.id.rv_sold);
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

