package com.andrienkom.vertolapp.mvvm.fragments;

import android.os.Bundle;
import android.provider.ContactsContract;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.constraint.Constraints.TAG;
import static android.view.View.resolveSize;

public class FragmentReadSdk extends Fragment {

    private TextView mText;

    private View mToolbar;
    private TextView mLabel;
    private ImageView mBtnBack;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_read_sdk_rostov,container,false);

        mToolbar = view.findViewById(R.id.fr_read_sdk_custom_toolbar);
        mLabel = view.findViewById(R.id.fr_read_sdk_label);
        mBtnBack = view.findViewById(R.id.fr_read_sdk_btn_back);

        mBtnBack.setOnClickListener(v->{
            getActivity().onBackPressed();
        });


        mText = view.findViewById(R.id.tv_sdk);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Consts.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<Other> other = api.getSDK("category");
        other.enqueue(new Callback<Other>() {
            @Override
            public void onResponse(Call<Other> call, Response<Other> response) {
                if (response.isSuccessful()){
                    mText.setText(response.body().toString());


                    Log.d(TAG, "onResponse: " + response.body());
                }else {
                    Log.d(TAG, "onResponse:  " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Other> call, Throwable t) {
                Log.d(TAG, "onFailure:  " + t);

            }
        });

        return view;
    }

    public static FragmentReadSdk newInstance(){
        return new FragmentReadSdk();
    }
}
