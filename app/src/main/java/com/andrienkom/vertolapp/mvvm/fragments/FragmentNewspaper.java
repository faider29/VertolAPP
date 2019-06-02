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

import com.andrienkom.vertolapp.MainActivity;
import com.andrienkom.vertolapp.R;
import com.andrienkom.vertolapp.entities.Articles;
import com.andrienkom.vertolapp.entities.Issues;
import com.andrienkom.vertolapp.mvvm.models.NewspaperModel;
import com.andrienkom.vertolapp.mvvm.viewModels.NewspaperViewModel;
import com.andrienkom.vertolapp.utility.adapters.ArticlesAdapter;
import com.andrienkom.vertolapp.utility.adapters.IssuesAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FragmentNewspaper extends Fragment {

    private View mToolbar;
    private RecyclerView mRVHorizontal;
    private RecyclerView mRVVertical;

    private NewspaperViewModel mNewspaperViewModel;
    private NewspaperModel mNewspaperModel;

    private IssuesAdapter mIssuesAdapter;
    private ArticlesAdapter mArticlesAdapter;

    private List<Articles> mArticlesList = new ArrayList<>();
    private List<Issues> mIssuesList = new ArrayList<>();

    private static String TAG = FragmentNewspaper.class.getSimpleName();


    public static FragmentNewspaper newInstance(){
        return new FragmentNewspaper();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_newspaper,container,false);

        mToolbar = view.findViewById(R.id.fr_newspaper_custom_toolbar);
        mIssuesAdapter = new IssuesAdapter(getContext(), mIssuesList);
        mArticlesAdapter = new ArticlesAdapter(getContext(),mArticlesList);

        mRVHorizontal = view.findViewById(R.id.rv_newspaper_issue);
        mRVHorizontal.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        mRVHorizontal.setAdapter(mIssuesAdapter);


       mRVVertical = view.findViewById(R.id.rv_newspaper_news);
       mRVVertical.setLayoutManager(new LinearLayoutManager(getContext()));
       mRVVertical.setAdapter(mArticlesAdapter);

//        mIssuesAdapter.setOnClickLister(position -> {
//           mArticlesViewModel.getIssuesFromId(mArticlesList.get(position).getId());
//       });

        /**
         * ДИЧЬ
         */
//        mIssuesAdapter.setOnItemClickListeners((position, issues) -> {
//            mNewspaperViewModel.getArticles();
//        });

        mArticlesAdapter.setOnItemClickListeners((position, articles) -> {
            ((MainActivity)getActivity()).addFragmentToBackStack(FragmentReadNewspaper.newInstance(articles));
        });



        observeIssues();
        observeArticles();
        return view;
    }

    private void observeIssues(){
        mNewspaperViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity()))
                .get(NewspaperViewModel.class);
        LiveData<List<Issues>> issues = mNewspaperViewModel.getIssues();
        issues.observe(getActivity(),issuesList -> {
            mIssuesList.clear();
            mIssuesList.addAll(issuesList);
            mIssuesAdapter.notifyDataSetChanged();
            Log.d(TAG, "observeIssues: "+ issuesList.size());
        });
    }


    private void observeArticles(){
        mNewspaperViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity()))
                .get(NewspaperViewModel.class);
        LiveData<List<Articles>> articles = mNewspaperViewModel.getArticles();
        articles.observe(getActivity(),articlesList -> {
            mArticlesList.clear();
            mArticlesList.addAll(articlesList);
            mArticlesAdapter.notifyDataSetChanged();
            Log.d(TAG, "observeArticles: " + articlesList.size());
        });
        LiveData<String> error = mNewspaperViewModel.getError();
        error.observe(getActivity(),errorMessage ->{

        });
    }





}
