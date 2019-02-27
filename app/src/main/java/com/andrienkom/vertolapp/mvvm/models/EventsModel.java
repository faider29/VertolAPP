package com.andrienkom.vertolapp.mvvm.models;

import com.andrienkom.vertolapp.entities.Events;
import com.andrienkom.vertolapp.interfaces.EventsModelListener;
import com.andrienkom.vertolapp.network.NetworkRepository;
import com.andrienkom.vertolapp.utility.Consts;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsModel {

    private Callback<JsonObject> mCallback;

    private List<EventsModelListener> mListeners = new ArrayList<>();

    public EventsModel(){
        initCallback();
    }

    public void start(){
//        NetworkRepository.getInstance().getEventsFrom(mCallback);
    }



    public void addListener(EventsModelListener listener){
        mListeners.add(listener);
    }


    public void removeListener(EventsModelListener listener){
        mListeners.remove(listener);
    }


    private void initCallback() {

        mCallback = new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    for (EventsModelListener listener: mListeners) {
                        listener.eventsListLoad(Events.getEventsFromJson(response.body()));
                    }
                }catch (ClassCastException e){
                    for (EventsModelListener listener: mListeners) {
                        listener.error(e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                for (EventsModelListener listener: mListeners) {
                    listener.error(t.getMessage());
                }
            }
        };
    }

    public void getEventsFrom(Consts.Category category, Consts.Month month) {
        NetworkRepository.getInstance().getEventsFrom(mCallback, category, month);
    }

}
