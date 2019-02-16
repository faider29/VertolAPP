package com.andrienkom.vertolapp.mvvm.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.card.MaterialCardView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andrienkom.vertolapp.MainActivity;
import com.andrienkom.vertolapp.R;



public class FragmentOther extends Fragment {


    private TextView mText;
    private MaterialCardView mMcFsk;
    private MaterialCardView mMcSDK;
    private MaterialCardView mMcYouth;

    private View mToolbar;
    private TextView mLabel;



    public static FragmentOther newInstance(){
        return new FragmentOther();
    }





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_other,container,false);

        mToolbar= view.findViewById(R.id.fr_other_custom_toolbar);
        mLabel = view.findViewById(R.id.fr_other_label);



        mText = view.findViewById(R.id.item_fck);
        mMcFsk = view.findViewById(R.id.materialCardViewFSK);
        mMcFsk.setOnClickListener(v -> {
            ((MainActivity) getActivity()).addFragmentToBackStack(FragmentReadFsk.newInstance());
        });


        mText = view.findViewById(R.id.item_sdk);
        mMcSDK = view.findViewById(R.id.materialCardViewSDK);
        mMcSDK.setOnClickListener(v -> {
            ((MainActivity) getActivity()).addFragmentToBackStack(FragmentReadSdk.newInstance());
        });

        mText = view.findViewById(R.id.item_youth);
        mMcYouth = view.findViewById(R.id.materialCardViewYOUTH);
        mMcYouth.setOnClickListener(v -> {
            ((MainActivity) getActivity()).addFragmentToBackStack(FragmentReadYouth.newInstance());
        });
        return view;
    }


}
