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
import android.widget.Toast;

import com.andrienkom.vertolapp.MainActivity;
import com.andrienkom.vertolapp.entities.News;
import com.andrienkom.vertolapp.utility.adapters.NewsAdapter;
import com.andrienkom.vertolapp.R;
import com.andrienkom.vertolapp.mvvm.viewModels.NewsViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FragmentNews extends Fragment {

    private RecyclerView mRecyclerView;
    private NewsAdapter mAdapter;
    private List<News> mNewsList = new ArrayList<>();

    private TextView mTitleTV;
    private View mToolbar;
    private ImageView mCalendar;
    private ImageView mBack;
    private Spinner mSpinnerSelectNews;
    private Spinner mSpinnerSelectMonth;


    private NewsViewModel mViewModel;

    private static String TAG = FragmentNews.class.getSimpleName();


    public static FragmentNews newInstance(){
        return new FragmentNews();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_news,container, false);

        mTitleTV = view.findViewById(R.id.fr_news_label);
        mToolbar = view.findViewById(R.id.fr_news_custom_toolbar);
        //mCalendar = view.findViewById(R.id.fr_news_btn_calendar);
        mBack = view.findViewById(R.id.fr_news_btn_back);
        mSpinnerSelectNews = view.findViewById(R.id.spinner_select_news);
        mSpinnerSelectMonth = view.findViewById(R.id.spinner_select_month);

        mAdapter = new NewsAdapter(getContext(), mNewsList);
        mAdapter.setOnItemClickListener((position, news) ->
                ((MainActivity) getActivity()).addFragmentToBackStack(FragmentReadNews.newInstance(news)));
        mRecyclerView = view.findViewById(R.id.rv_news);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);

        mSpinnerSelectNews.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.dropdown_select_news)));
        mSpinnerSelectNews.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemSelected: " + getResources().getStringArray(R.array.dropdown_select_news)[position]);
//                mViewModel.getNewsFrom(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

       ArrayAdapter<String> spinneradapter = new ArrayAdapter<String>(getContext(),R.layout.spiner_item_news,getResources().getStringArray(R.array.dropdown_select_news));
       spinneradapter.setDropDownViewResource(R.layout.spiner_dropdown_item_news);
       mSpinnerSelectNews.setAdapter(spinneradapter);
       mSpinnerSelectNews.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               mViewModel.getNewsFromCategory(position);
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });

        mBack.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });

        observe();
        return view;
    }



    private void observe() {
        mViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(NewsViewModel.class);
        LiveData<List<News>> articles = mViewModel.getArticles();
        articles.observe(getActivity(), articleList -> {
            /**
             * Тут тебе приходит список News который необходимо засунуть в адаптер
             * и вызвать у адаптера NotifyDataSetChange чтобы данные обновились на экране
             */
            mNewsList.clear();
            mNewsList.addAll(articleList);
            mAdapter.notifyDataSetChanged();
            Log.d(TAG, "observe: " + articleList.size());

        });

        LiveData<String> error = mViewModel.getError();
        error.observe(getActivity(), errorMessage -> {
            Toast.makeText(getContext(),"Извинте, в выбранном месяце нету новостей",Toast.LENGTH_LONG).show();

        });
    }

}
