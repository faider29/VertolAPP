package com.andrienkom.vertolapp.mvvm.viewModels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.andrienkom.vertolapp.entities.Events;
import com.andrienkom.vertolapp.interfaces.EventsModelListener;
import com.andrienkom.vertolapp.mvvm.models.EventsModel;

import java.util.List;

public class EventsViewModel extends ViewModel implements EventsModelListener {

    private EventsModel mEventsModel;

    private MutableLiveData<List<Events>> mEvents = new MutableLiveData<>();
    private MutableLiveData<String> mError = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsLoad = new MutableLiveData<>();

    public EventsViewModel(){
        mEventsModel = new EventsModel();
        mEventsModel.addListener(this);
        mIsLoad.postValue(true);
        mEventsModel.start();
    }



    @Override
    public void eventsListLoad(List<Events> events) {
        mIsLoad.postValue(false);
        mEvents.postValue(events);

    }

    @Override
    public void error(String errorMessage) {
        mIsLoad.postValue(false);
        mError.postValue("Внимание произошла ошибка: \n" + errorMessage);

    }

    @Override
    protected void onCleared() {
        mEventsModel.removeListener(this);
        mEventsModel = null;
        super.onCleared();
    }

    public MutableLiveData<List<Events>> getEvents() {
        return mEvents;
    }


    public MutableLiveData<String> getError() {
        return mError;
    }


    public MutableLiveData<Boolean> getIsLoad() {
        return mIsLoad;
    }
}
