package com.andrienkom.vertolapp.mvvm.models;


import android.util.Log;
import android.widget.Toast;

import com.andrienkom.vertolapp.entities.News;
import com.andrienkom.vertolapp.network.NetworkRepository;
import com.andrienkom.vertolapp.interfaces.MainModelListener;

import com.andrienkom.vertolapp.utility.Consts;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsModel {

    private Callback<JsonObject> mCallback;

    private static final String TAG = NewsModel.class.getSimpleName();

    private List<MainModelListener> mListeners = new ArrayList<>();

    public NewsModel() {
        initCallbacks();
    }

    /**
     * запрашиваем данные
     */
    public void start() {
        NetworkRepository.getInstance().getAllNews(mCallback);
    }


    public void getNewsFrom(Consts.Category category) {
        NetworkRepository.getInstance().getNewsFromCategory(mCallback, category);
    }


    /**
     * Добавляем слушателя в список на оповещение
     * @param listener - слушатель который хотим добавить
     */
    public void addListener(MainModelListener listener) {
        mListeners.add(listener);
    }

    /**
     * Убираем слушателя из списка оповещений
     * ВНИМАНИЕ!!! за жизненный цикл слушателей отвечает подписчик а не издатель,
     * поэтому чтобы не словить утечку памяти не забывай отписываться в деструкторе
     * @param listener - слушатель который хотим удалить
     */
    public void removeListener(MainModelListener listener) {
        mListeners.remove(listener);
    }

    private void initCallbacks() {


        mCallback = new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d(TAG, "onResponse: " + call.request().toString());
                try {

                    for (MainModelListener listener : mListeners) {
                        listener.articlesListLoad(News.getNewsFromJson(response.body()));
                    }
                }catch (ClassCastException e){
                    for (MainModelListener listener: mListeners) {
                        listener.error(e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                for (MainModelListener listener: mListeners) {
                    listener.error(t.getMessage());
                }
            }
        };

    }

}

