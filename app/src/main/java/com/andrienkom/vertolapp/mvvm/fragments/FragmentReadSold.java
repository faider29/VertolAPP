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
import com.andrienkom.vertolapp.entities.Sold;
import com.andrienkom.vertolapp.utility.Consts;
import com.squareup.picasso.Picasso;


public class FragmentReadSold extends Fragment {

    private ImageView mImageView;
    private TextView mHeader;
    private TextView mText;
    private TextView mDate;

    public static final String SOLD_KEY = "sold_key";

    public Sold sold;


    public static FragmentReadSold newInstance(Sold sold){
        FragmentReadSold fragmentReadSold = new FragmentReadSold();

        Bundle bundle = new Bundle();
        bundle.putSerializable(SOLD_KEY, sold);
        fragmentReadSold.setArguments(bundle);
        return  fragmentReadSold;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_read_sold,container,false);
        sold = (Sold) getArguments().getSerializable(SOLD_KEY);

        mHeader = view.findViewById(R.id.tv_read_header_sold);
        mHeader.setText(sold.getTitle());

        mText = view.findViewById(R.id.tv_read_text_sold);
        mText.setText(sold.getText());

        mDate = view.findViewById(R.id.tv_read_date_sold);
        mDate.setText(sold.getDate());

        mImageView = view.findViewById(R.id.iv_read_sold);
        Picasso.get()
                .load(Consts.BASE_URL + sold.getImg())
                .error(R.drawable.error)
                .into(getSoldImage());


        return view;
    }

    private ImageView getSoldImage() {
        return mImageView;
    }
}
