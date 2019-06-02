package com.andrienkom.vertolapp.mvvm.models;

import android.util.Log;

import com.andrienkom.vertolapp.entities.Articles;
import com.andrienkom.vertolapp.entities.Issues;
import com.andrienkom.vertolapp.interfaces.ArticlesModelListener;
import com.andrienkom.vertolapp.interfaces.IssuesModelListener;
import com.andrienkom.vertolapp.network.NetworkRepository;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Maxim Andrienko
 * 5/26/19
 */
public class NewspaperModel {

    public static final String TAG = NewspaperModel.class.getSimpleName();

    private Callback<JsonObject> mCallbackIssues;
    private Callback<JsonObject> mCallbackArticles;

    private List<IssuesModelListener> mListenersIssues = new ArrayList<>();
    private List<ArticlesModelListener> mListenersArticles = new ArrayList<>();

    public NewspaperModel(){
        initCallbackIssues();
        initCallbackIssuesID();
//        initCallbackArticles(); // если добавить тогда не скачиваются список выпусков
    }

    public void start(){
        NetworkRepository.getInstance().getIssues(mCallbackIssues);
    }

    public void addListenerIssues(IssuesModelListener issuesModelListener){
        mListenersIssues.add(issuesModelListener);
    }
    public void removeListenerIssues(IssuesModelListener issuesModelListener){
        mListenersIssues.remove(issuesModelListener);
    }

    public void addListenerArticles(ArticlesModelListener articlesModelListener){
        mListenersArticles.add(articlesModelListener);
    }
    public void removeListenerArticles(ArticlesModelListener articlesModelListener){
        mListenersArticles.remove(articlesModelListener);
    }


    private void initCallbackIssues() {
        mCallbackIssues = new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                List<Issues> issues = Issues.getIssuesFromJson(response.body());

                int lastId = -1;
                for (Issues issue: issues) {
                    if (issue.getId() > lastId) lastId = issue.getId();
                }
                if (lastId != -1) getIssuesFromId(lastId);
//                getIssuesFromId(4);
                for(IssuesModelListener issuesModelListener : mListenersIssues){
                    issuesModelListener.issuesListLoad(issues);

                    Log.d(TAG, "onResponse: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                for (IssuesModelListener issuesModelListener: mListenersIssues){
                    issuesModelListener.error(t.getMessage());
                }
            }
        };
    }

    private void initCallbackIssuesID() {
        mCallbackArticles = new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                for (ArticlesModelListener listener: mListenersArticles){
                    listener.articlesListLoad(Articles.getArticlesFromJson(response.body()));
                    Log.d("onResponseArticles ", "onResponseArticles: " + response.body());
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                for (ArticlesModelListener listener: mListenersArticles){
                    listener.error(t.getMessage());
                }
            }
        };
    }

    public void getIssuesFromId(int id) {
        NetworkRepository.getInstance().getArticles(mCallbackArticles, String.valueOf(id));
    }


}
