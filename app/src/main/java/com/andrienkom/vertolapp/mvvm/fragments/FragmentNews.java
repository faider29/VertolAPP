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

    private NewsViewModel mViewModel;

    private static String TAG = FragmentNews.class.getSimpleName();


    public static FragmentNews newInstance(){
        return new FragmentNews();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_news,container, false);

        mAdapter = new NewsAdapter(getContext(), mNewsList);
        mAdapter.setOnItemClickListener((position, news) -> {

        });
        mRecyclerView = view.findViewById(R.id.rv_news);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
       /* Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://46.229.215.224:9999/api/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        final Api api = retrofit.create(Api.class);

        Call<JsonObject> j = api.getNewsUser("all", "null");
        j.enqueue(new Callback<JsonObject>() {

                      @Override
                      public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                          if (response.isSuccessful()){

                              JsonObject jsonObject = response.body();
                              Log.d(TAG, "api" + jsonObject.getAsString());
                          }

                      }

                      @Override
                      public void onFailure(Call<JsonObject> call, Throwable t) {

                          Log.d(TAG , "error" + t.getMessage());
                      }
                  });*/



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

        });
    }

}