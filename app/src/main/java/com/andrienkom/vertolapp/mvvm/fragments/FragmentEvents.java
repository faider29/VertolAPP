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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.andrienkom.vertolapp.MainActivity;
import com.andrienkom.vertolapp.R;
import com.andrienkom.vertolapp.entities.Events;
import com.andrienkom.vertolapp.mvvm.viewModels.EventsViewModel;
import com.andrienkom.vertolapp.utility.adapters.EventsAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.support.constraint.Constraints.TAG;

public class FragmentEvents extends Fragment {


    private RecyclerView mRecyclerView;
    private EventsAdapter mEventsAdapter;
    private List<Events> mEventsList = new ArrayList<>();

    private View mToolbar;
    private TextView mTitleTV;
    private Spinner mSpinnerSelectMonth;

    private EventsViewModel mEventsViewModel;


    public static FragmentEvents newInstance(){
        return new FragmentEvents();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fr_events,container,false);

       mToolbar = view.findViewById(R.id.fr_events_custom_toolbar);
       mTitleTV = view.findViewById(R.id.fr_events_label);
       mSpinnerSelectMonth = view.findViewById(R.id.spinner_select_month);

       mEventsAdapter = new EventsAdapter(getContext(),mEventsList);

       mEventsAdapter.setOnItemClickListener((position, events) -> ((MainActivity) Objects.requireNonNull(getActivity()))
               .addFragmentToBackStack(FragmentReadEvents.newInstance(events)));

       mRecyclerView = view.findViewById(R.id.rv_events);
       mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
       mRecyclerView.setAdapter(mEventsAdapter);


        ArrayAdapter<String> spinnerAdapterMonth = new ArrayAdapter<String>(getContext(),R.layout.spiner_item_month,getResources().getStringArray(R.array.dropdown_month));
        spinnerAdapterMonth.setDropDownViewResource(R.layout.spiner_dropdown_item_month);
        mSpinnerSelectMonth.setAdapter(spinnerAdapterMonth);
        mSpinnerSelectMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Log.d(TAG, "onCreateView: onCreateView() ");

       observe();
       return view;
    }


    private void observe() {
        mEventsViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(EventsViewModel.class);
        LiveData<List<Events>> events = mEventsViewModel.getEvents();
        events.observe(getActivity(), eventsList -> {
            mEventsList.clear();
            mEventsList.addAll(eventsList);
            mEventsAdapter.notifyDataSetChanged();


        });

        LiveData<String> error = mEventsViewModel.getError();
        error.observe(getActivity(), errorMessage -> {

        });
    }
}
