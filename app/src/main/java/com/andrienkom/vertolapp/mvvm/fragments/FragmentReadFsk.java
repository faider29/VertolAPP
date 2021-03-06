package com.andrienkom.vertolapp.mvvm.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andrienkom.vertolapp.R;
import com.andrienkom.vertolapp.entities.Other;
import com.andrienkom.vertolapp.interfaces.Api;
import com.andrienkom.vertolapp.utility.Consts;
import com.google.gson.JsonObject;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.constraint.Constraints.TAG;

public class FragmentReadFsk extends Fragment {

    private TextView mText;

    private View mToolbar;
    private ImageView mBtnBack;
    private TextView mLabel;


    public static FragmentReadFsk newInstance() {
        return new FragmentReadFsk();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_read_fsk_strela, container, false);

        mToolbar = view.findViewById(R.id.fr_read_fsk_custom_toolbar);
        mBtnBack = view.findViewById(R.id.fr_read_fsk_btn_back);
        mLabel = view.findViewById(R.id.fr_read_fsk_label);
        
        mText = view.findViewById(R.id.tv_fck);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Consts.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<Other> other = api.getFCK("category");
        other.enqueue(new Callback<Other>() {

            @Override
            public void onResponse(Call<Other> call, Response<Other> response) {
                if (response.isSuccessful()){
                    mText.setText(response.body().toString());


                    Log.d(TAG, "onResponse: " +response.body());
                } else {
                    Log.d(TAG, "onResponse: " + response.code());
                }

            }

            @Override
            public void onFailure(Call<Other>call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);

            }

        });

        mBtnBack.setOnClickListener(v->{
            getActivity().onBackPressed();
        });


        return view;
    }
}










