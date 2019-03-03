package com.andrienkom.vertolapp.mvvm.models;

import android.util.Log;

import com.andrienkom.vertolapp.entities.Events;
import com.andrienkom.vertolapp.interfaces.CalendarModelListener;
import com.andrienkom.vertolapp.network.NetworkRepository;
import com.andrienkom.vertolapp.utility.Consts;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalendarModel {

    private Callback<JsonObject> mCallback;

    private List<CalendarModelListener> mListeners = new ArrayList<>();

    public CalendarModel(){
        initCallback();
    }




    public void addListener(CalendarModelListener listener){
        mListeners.add(listener);
    }


    public void removeListener(CalendarModelListener listener){
        mListeners.remove(listener);
    }


    private void initCallback() {

        mCallback = new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    for (CalendarModelListener listener: mListeners) {
                        listener.eventsListLoad(Events.getEventsFromJson(response.body()));
                    }
                }catch (ClassCastException e){
                    for (CalendarModelListener listener: mListeners) {
                        listener.error(e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                for (CalendarModelListener listener: mListeners) {
                    listener.error(t.getMessage());
                }
            }
        };
    }

    public void getEventsFrom(Consts.Category category, Consts.Month month) {
        NetworkRepository.getInstance().getEventsFrom(mCallback, category, month);
    }

}
