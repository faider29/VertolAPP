package com.andrienkom.vertolapp.mvvm.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andrienkom.vertolapp.R;
import com.andrienkom.vertolapp.entities.Articles;

/**
 * Created by Maxim Andrienko
 * 6/2/19
 */
public class FragmentReadNewspaper  extends Fragment {


    private View mToolbar;
    private ImageView mBtnBack;
    private TextView mLabel;

    private TextView mTitle;
    private TextView mText;

    public Articles mArticles;

    private static final String NEWSPAPER_KEY = "newspaper_key";

    public static FragmentReadNewspaper newInstance(Articles articles){
        FragmentReadNewspaper fragmentReadNewspaper = new FragmentReadNewspaper();

        Bundle bundle = new Bundle();
        bundle.putSerializable(NEWSPAPER_KEY,articles);
        fragmentReadNewspaper.setArguments(bundle);
        return fragmentReadNewspaper;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_read_newspaper,container,false);
        mArticles = (Articles) getArguments().getSerializable(NEWSPAPER_KEY);

        mToolbar = view.findViewById(R.id.fr_newspaper_custom_toolbar);
        mBtnBack = view.findViewById(R.id.fr_newspaper_btn_back);
        mBtnBack.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });
        mLabel = view.findViewById(R.id.fr_newspaper_label);

        mTitle = view.findViewById(R.id.tv_read_header_newspaper);
        mTitle.setText(mArticles.getTitle());

        mText = view.findViewById(R.id.tv_read_text_newspaper);
        mText.setText(mArticles.getText());

        return view;
    }
}
