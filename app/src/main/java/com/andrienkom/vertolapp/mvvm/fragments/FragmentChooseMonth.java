package com.andrienkom.vertolapp.mvvm.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.Toast;

import com.andrienkom.vertolapp.MainActivity;
import com.andrienkom.vertolapp.R;
import com.andrienkom.vertolapp.entities.Events;
import com.andrienkom.vertolapp.utility.Consts;


public class FragmentChooseMonth extends Fragment {

    private View mToolbar;
    private TableLayout mTableLayout;
    private View mView;
    private Events mEvents;


    public static FragmentChooseMonth newInstance(){
        return new FragmentChooseMonth();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fr__calendar_choose_month,container,false);

        mToolbar = mView.findViewById(R.id.fr_events_choose_month_custom_toolbar);
        mTableLayout = mView.findViewById(R.id.month_table);


        initViews();
        return mView;
    }

    private void initViews() {
        mView.findViewById(R.id.btn_fr_calendar_december).setOnClickListener(view -> {
            ((MainActivity) getActivity()).addFragmentToBackStack(FragmentCalendar.newInstance(Consts.Month.december));
        });
        mView.findViewById(R.id.btn_fr_calendar_january).setOnClickListener(view ->{
//           if (){
               ((MainActivity) getActivity()).addFragmentToBackStack(FragmentCalendar.newInstance(Consts.Month.january));
//           }
        });
        mView.findViewById(R.id.btn_fr_calendar_february).setOnClickListener(view ->{

            ((MainActivity) getActivity()).addFragmentToBackStack(FragmentCalendar.newInstance(Consts.Month.february));
        });
        mView.findViewById(R.id.btn_fr_calendar_march).setOnClickListener(view ->{
            ((MainActivity) getActivity()).addFragmentToBackStack(FragmentCalendar.newInstance(Consts.Month.march));
        });
        mView.findViewById(R.id.btn_fr_calendar_april).setOnClickListener(view ->{
            ((MainActivity) getActivity()).addFragmentToBackStack(FragmentCalendar.newInstance(Consts.Month.april));
        });
        mView.findViewById(R.id.btn_fr_calendar_may).setOnClickListener(view ->{
            ((MainActivity) getActivity()).addFragmentToBackStack(FragmentCalendar.newInstance(Consts.Month.may));
        });
        mView.findViewById(R.id.btn_fr_calendar_june).setOnClickListener(view ->{
            ((MainActivity) getActivity()).addFragmentToBackStack(FragmentCalendar.newInstance(Consts.Month.june));
        });
        mView.findViewById(R.id.btn_fr_calendar_july).setOnClickListener(view ->{
            ((MainActivity) getActivity()).addFragmentToBackStack(FragmentCalendar.newInstance(Consts.Month.july));
        });
        mView.findViewById(R.id.btn_fr_calendar_august).setOnClickListener(view ->{
            ((MainActivity) getActivity()).addFragmentToBackStack(FragmentCalendar.newInstance(Consts.Month.august));
        });
        mView.findViewById(R.id.btn_fr_calendar_september).setOnClickListener(view ->{
            ((MainActivity) getActivity()).addFragmentToBackStack(FragmentCalendar.newInstance(Consts.Month.september));
        });
        mView.findViewById(R.id.btn_fr_calendar_october).setOnClickListener(view ->{
            ((MainActivity) getActivity()).addFragmentToBackStack(FragmentCalendar.newInstance(Consts.Month.october));
        });
        mView.findViewById(R.id.btn_fr_calendar_november).setOnClickListener(view ->{
            ((MainActivity) getActivity()).addFragmentToBackStack(FragmentCalendar.newInstance(Consts.Month.november));
        });
    }

}
