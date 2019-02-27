package com.andrienkom.vertolapp.mvvm.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.andrienkom.vertolapp.MainActivity;
import com.andrienkom.vertolapp.R;
import com.andrienkom.vertolapp.utility.Consts;


public class FragmentChooseMonth extends Fragment {

    private View mToolbar;
    private TableLayout mTableLayout;
    private View mView;


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
        mView.findViewById(R.id.btn_fr_events_december).setOnClickListener(view -> {
            ((MainActivity) getActivity()).addFragmentToBackStack(FragmentCalendar.newInstance(Consts.Month.december));
        });
        mView.findViewById(R.id.btn_fr_events_january).setOnClickListener(view ->{
            ((MainActivity) getActivity()).addFragmentToBackStack(FragmentCalendar.newInstance(Consts.Month.january));
        });
        mView.findViewById(R.id.btn_fr_events_february).setOnClickListener(view ->{
            ((MainActivity) getActivity()).addFragmentToBackStack(FragmentCalendar.newInstance(Consts.Month.february));
        });
        mView.findViewById(R.id.btn_fr_events_march).setOnClickListener(view ->{
            ((MainActivity) getActivity()).addFragmentToBackStack(FragmentCalendar.newInstance(Consts.Month.march));
        });
        mView.findViewById(R.id.btn_fr_events_april).setOnClickListener(view ->{
            ((MainActivity) getActivity()).addFragmentToBackStack(FragmentCalendar.newInstance(Consts.Month.april));
        });
        mView.findViewById(R.id.btn_fr_events_may).setOnClickListener(view ->{
            ((MainActivity) getActivity()).addFragmentToBackStack(FragmentCalendar.newInstance(Consts.Month.may));
        });
        mView.findViewById(R.id.btn_fr_events_june).setOnClickListener(view ->{
            ((MainActivity) getActivity()).addFragmentToBackStack(FragmentCalendar.newInstance(Consts.Month.june));
        });
        mView.findViewById(R.id.btn_fr_events_july).setOnClickListener(view ->{
            ((MainActivity) getActivity()).addFragmentToBackStack(FragmentCalendar.newInstance(Consts.Month.july));
        });
        mView.findViewById(R.id.btn_fr_events_august).setOnClickListener(view ->{
            ((MainActivity) getActivity()).addFragmentToBackStack(FragmentCalendar.newInstance(Consts.Month.august));
        });
        mView.findViewById(R.id.btn_fr_events_september).setOnClickListener(view ->{
            ((MainActivity) getActivity()).addFragmentToBackStack(FragmentCalendar.newInstance(Consts.Month.september));
        });
        mView.findViewById(R.id.btn_fr_events_october).setOnClickListener(view ->{
            ((MainActivity) getActivity()).addFragmentToBackStack(FragmentCalendar.newInstance(Consts.Month.october));
        });
        mView.findViewById(R.id.btn_fr_events_november).setOnClickListener(view ->{
            ((MainActivity) getActivity()).addFragmentToBackStack(FragmentCalendar.newInstance(Consts.Month.november));
        });
    }

}
