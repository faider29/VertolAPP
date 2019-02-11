package com.andrienkom.vertolapp.mvvm.fragments;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andrienkom.vertolapp.MainActivity;
import com.andrienkom.vertolapp.R;
import com.andrienkom.vertolapp.entities.Events;
import com.andrienkom.vertolapp.mvvm.viewModels.EventsViewModel;
import com.andrienkom.vertolapp.utility.adapters.EventsAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FragmentEvents extends Fragment {


    private RecyclerView mRecyclerView;
    private EventsAdapter mEventsAdapter;
    private List<Events> mEventsList = new ArrayList<>();

    private EventsViewModel mEventsViewModel;


    public static FragmentEvents newInstance(){
        return new FragmentEvents();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fr_events,container,false);

       mEventsAdapter = new EventsAdapter(getContext(),mEventsList);

       mEventsAdapter.setOnItemClickListener((position, events) -> ((MainActivity) Objects.requireNonNull(getActivity()))
               .addFragment(FragmentReadEvents.newInstance(events)));

       mRecyclerView = view.findViewById(R.id.rv_events);
       mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
       mRecyclerView.setAdapter(mEventsAdapter);
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
