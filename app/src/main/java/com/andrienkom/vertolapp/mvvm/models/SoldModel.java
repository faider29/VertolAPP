package com.andrienkom.vertolapp.mvvm.models;

import com.andrienkom.vertolapp.entities.Sold;
import com.andrienkom.vertolapp.interfaces.SoldModelListener;
import com.andrienkom.vertolapp.network.NetworkRepository;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SoldModel {


    private Callback<JsonObject> mCallback;



    private List<SoldModelListener> mListeners = new ArrayList<>();

    public SoldModel() {
        initCallbacks();
    }

    /**
     * запрашиваем данные
     */
    public void start() {
        NetworkRepository.getInstance().getSold(mCallback);
    }



    /**
     * Добавляем слушателя в список на оповещение
     * @param listener - слушатель который хотим добавить
     */
    public void addListener(SoldModelListener listener) {
        mListeners.add(listener);
    }


    public void removeListener(SoldModelListener listener) {
        mListeners.remove(listener);
    }

    private void initCallbacks() {


        mCallback = new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                for (SoldModelListener listener: mListeners) {
                    listener.soldListLoad(Sold.getSoldFromJson(response.body()));
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                for (SoldModelListener listener: mListeners) {
                    listener.error(t.getMessage());
                }
            }
        };

    }
}
