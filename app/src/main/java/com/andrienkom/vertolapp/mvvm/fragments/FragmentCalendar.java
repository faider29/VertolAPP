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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.andrienkom.vertolapp.MainActivity;
import com.andrienkom.vertolapp.R;
import com.andrienkom.vertolapp.entities.Events;
import com.andrienkom.vertolapp.mvvm.viewModels.CalendarViewModel;
import com.andrienkom.vertolapp.utility.Consts;
import com.andrienkom.vertolapp.utility.adapters.EventsAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FragmentCalendar extends Fragment {


    private RecyclerView mRecyclerView;
    private EventsAdapter mEventsAdapter;
    private List<Events> mEventsList = new ArrayList<>();

    private View mToolbar;
    private TextView mTitleTV;
    private Spinner mSpinnerSelectCategory;


    private CalendarViewModel mCalendarViewModel;
    private static final String DESCRIBABLE_KEY = "describable_key";

    private static String TAG = FragmentCalendar.class.getSimpleName();

    private Consts.Month mCurMonth;


    public static FragmentCalendar newInstance(Consts.Month event){
        FragmentCalendar fragment = new FragmentCalendar();
        Bundle bundle = new Bundle();
        bundle.putSerializable(DESCRIBABLE_KEY, event);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fr_calendar,container,false);
       mCurMonth = (Consts.Month) getArguments().getSerializable(DESCRIBABLE_KEY);

       mToolbar = view.findViewById(R.id.fr_calendar_custom_toolbar);
       mTitleTV = view.findViewById(R.id.fr_calendar_label);
       mSpinnerSelectCategory = view.findViewById(R.id.spinner_select_category);

       mEventsAdapter = new EventsAdapter(getContext(),mEventsList);

       mEventsAdapter.setOnItemClickListener((position, events) -> ((MainActivity) Objects.requireNonNull(getActivity()))
               .addFragmentToBackStack(FragmentReadCalendar.newInstance(events)));

       mRecyclerView = view.findViewById(R.id.rv_events);
       mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
       mRecyclerView.setAdapter(mEventsAdapter);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(),R.layout.spiner_item_news,getResources().getStringArray(R.array.dropdown_select_news));
        spinnerAdapter.setDropDownViewResource(R.layout.spiner_dropdown_item_news);
        mSpinnerSelectCategory.setAdapter(spinnerAdapter);
        mSpinnerSelectCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                mCalendarViewModel.getEventsFrom(position, mCurMonth);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        observe();
       return view;
    }


    private void observe() {
        mCalendarViewModel = ViewModelProviders.of((this)).get(CalendarViewModel.class);
        LiveData<List<Events>> events = mCalendarViewModel.getEvents();
        events.observe(getActivity(), eventsList -> {
            Log.d(TAG, "observe: " + eventsList.size());
            mEventsList.clear();
            mEventsList.addAll(eventsList);
            mEventsAdapter.notifyDataSetChanged();


        });

        LiveData<String> error = mCalendarViewModel.getError();
        error.observe(getActivity(), errorMessage -> {

            if (getActivity() != null)
            ((MainActivity) getActivity()).showError("Месяц не заполнен");
        });
    }
}
