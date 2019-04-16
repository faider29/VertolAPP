package com.andrienkom.vertolapp.mvvm.models;

import com.andrienkom.vertolapp.entities.Articles;
import com.andrienkom.vertolapp.interfaces.ArticlesModelListener;
import com.andrienkom.vertolapp.network.NetworkRepository;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticlesModel {
    private Callback<JsonObject> mCallback;

    private List<ArticlesModelListener> mListeners = new ArrayList<>();

    public ArticlesModel(){
        initCallback();
    }

    public void start(String id){
        NetworkRepository.getInstance().getArticles(mCallback, id);
    }

    public void addListener(ArticlesModelListener listener){
        mListeners.add(listener);
    }
    public void removeListener(ArticlesModelListener listener){
        mListeners.remove(listener);
    }

    public void initCallback(){
        mCallback = new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                for (ArticlesModelListener listener: mListeners){
                    listener.articlesListLoad(Articles.getArticlesFromJson(response.body()));
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                for (ArticlesModelListener listener: mListeners){
                    listener.error(t.getMessage());
                }
            }
        };

    }
}
