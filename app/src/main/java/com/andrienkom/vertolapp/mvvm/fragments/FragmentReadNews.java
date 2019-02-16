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

import com.andrienkom.vertolapp.MainActivity;
import com.andrienkom.vertolapp.R;
import com.andrienkom.vertolapp.entities.News;
import com.andrienkom.vertolapp.utility.Consts;
import com.squareup.picasso.Picasso;

public class FragmentReadNews extends Fragment {


    private ImageView mImageView;
    private TextView mHeader;
    private TextView mText;
    private TextView mDate;


    private View mToolbar;
    private ImageView mBtnBack;
    private TextView mLabel;

    private static final String DESCRIBABLE_KEY = "describable_key";

    public News news;

    public static FragmentReadNews newInstance(News news) {
        FragmentReadNews fragment = new FragmentReadNews();
        Bundle bundle = new Bundle();
        bundle.putSerializable(DESCRIBABLE_KEY, news);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_read_news,container, false);
        news = (News) getArguments().getSerializable(DESCRIBABLE_KEY);


        mToolbar = view.findViewById(R.id.fr_news_custom_toolbar);
        mBtnBack = view.findViewById(R.id.fr_news_btn_back);
        mLabel = view.findViewById(R.id.fr_news_label);

        mBtnBack.setOnClickListener(v->{
            getActivity().onBackPressed();
        });


        mHeader = view.findViewById(R.id.tv_read_header_news);
        mHeader.setText(news.getTitle());

        mText = view.findViewById(R.id.tv_read_text_news);
        mText.setText(news.getText());

        mDate = view.findViewById(R.id.tv_read_news_date);
        mDate.setText(news.getDate());

        mImageView = view.findViewById(R.id.iv_read_news);
        Picasso.get()
                .load(Consts.BASE_URL + news.getImg())
                .error(R.drawable.error)
                .into(getNewsImage());

        return view;
    }
    public ImageView getNewsImage() {
        return mImageView;
    }


}
