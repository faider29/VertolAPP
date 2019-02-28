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
import com.andrienkom.vertolapp.entities.Events;
import com.andrienkom.vertolapp.utility.Consts;
import com.squareup.picasso.Picasso;


public class FragmentReadCalendar extends Fragment {

    private static final String EVENTS_KEY = "events_key";

    public Events events;

    private ImageView mImageView;
    private TextView mHeader;
    private TextView mText;
    private TextView mDate;


    private View mToolbar;
    private ImageView mBtnBack;
    private TextView mTextView;






    public static FragmentReadCalendar newInstance(Events events){
        FragmentReadCalendar fragmentReadCalendar = new FragmentReadCalendar();

        Bundle bundle = new Bundle();
        bundle.putSerializable(EVENTS_KEY,  events);
        fragmentReadCalendar.setArguments(bundle);

        return fragmentReadCalendar;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fr_read_calendar, container, false);
        events = (Events) getArguments().getSerializable(EVENTS_KEY);

        mToolbar = view.findViewById(R.id.fr_read_calendar_custom_toolbar);
        mBtnBack = view.findViewById(R.id.fr_read_calendar_btn_back);
        mTextView = view.findViewById(R.id.fr_read_calendar_label);


        mHeader = view.findViewById(R.id.tv_read_header_calendar);
        mHeader.setText(events.getTitle());

        mText = view.findViewById(R.id.tv_read_text_calendar);
        mText.setText(events.getText());

        mDate = view.findViewById(R.id.tv_read_date_calendar);
        mDate.setText(events.getDate());

        mImageView = view.findViewById(R.id.iv_read_calendar);
        Picasso.get()
                .load(Consts.BASE_URL + events.getImg())
                .error(R.drawable.error)
                .into(getEventsImage());

        mBtnBack.setOnClickListener(v->{
            getActivity().onBackPressed();
        });

        return view;
    }


    public ImageView getEventsImage() {
        return mImageView;
    }

}
