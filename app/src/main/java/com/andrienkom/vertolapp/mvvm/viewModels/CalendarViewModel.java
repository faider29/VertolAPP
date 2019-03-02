package com.andrienkom.vertolapp.mvvm.viewModels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.andrienkom.vertolapp.entities.Events;
import com.andrienkom.vertolapp.interfaces.CalendarModelListener;
import com.andrienkom.vertolapp.mvvm.models.EventsModel;
import com.andrienkom.vertolapp.utility.Consts;

import java.util.List;

public class CalendarViewModel extends ViewModel implements CalendarModelListener {

    private EventsModel mEventsModel;
//
//    private Consts.Category mCategory = Consts.Category.all;
//    private Consts.Month mSelectMonth = Consts.Month.january;

    private MutableLiveData<List<Events>> mEvents = new MutableLiveData<>();
    private MutableLiveData<String> mError = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsLoad = new MutableLiveData<>();

    public CalendarViewModel(){
        mEventsModel = new EventsModel();
        mEventsModel.addListener(this);
        mIsLoad.postValue(true);
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


    public void getEventsFrom(int position, Consts.Month month) {
        mIsLoad.postValue(true);
        switch (position) {
            case 0:
                mEventsModel.getEventsFrom(Consts.Category.all, month);
                break;
            case 1:
                mEventsModel.getEventsFrom(Consts.Category.fsk, month);
                break;
            case 2:
                mEventsModel.getEventsFrom(Consts.Category.sdk, month);
                break;
            case 3:
                mEventsModel.getEventsFrom(Consts.Category.ry, month);
                break;
        }


    }

//    public void getEventsFromMonth(Consts.Month month) {
//        mIsLoad.postValue(true);
////        switch (position){
////            case 0:
////                mEventsModel.getEventsFrom(mCategory,Consts.Month.all);
////                mSelectMonth = Consts.Month.all;
////                break;
////            case 1:
////                mEventsModel.getEventsFrom(mCategory,Consts.Month.january);
////                mSelectMonth = Consts.Month.january;
////                break;
////            case 2:
////                mEventsModel.getEventsFrom(mCategory,Consts.Month.february);
////                mSelectMonth = Consts.Month.february;
////                break;
////            case 3:
////                mEventsModel.getEventsFrom(mCategory,Consts.Month.march);
////                mSelectMonth = Consts.Month.march;
////                break;
////            case 4:
////                mEventsModel.getEventsFrom(mCategory,Consts.Month.april);
////                mSelectMonth = Consts.Month.april;
////                break;
////            case 5:
////                mEventsModel.getEventsFrom(mCategory,Consts.Month.may);
////                mSelectMonth = Consts.Month.may;
////                break;
////            case 6:
////                mEventsModel.getEventsFrom(mCategory,Consts.Month.june);
////                mSelectMonth = Consts.Month.june;
////                break;
////            case 7:
////                mEventsModel.getEventsFrom(mCategory,Consts.Month.july);
////                mSelectMonth = Consts.Month.july;
////                break;
////            case 8:
////                mEventsModel.getEventsFrom(mCategory,Consts.Month.august);
////                mSelectMonth = Consts.Month.august;
////                break;
////            case 9:
////                mEventsModel.getEventsFrom(mCategory,Consts.Month.september);
////                mSelectMonth = Consts.Month.september;
////                break;
////            case 10:
////                mEventsModel.getEventsFrom(mCategory,Consts.Month.october);
////                mSelectMonth = Consts.Month.october;
////                break;
////            case 11:
////                mEventsModel.getEventsFrom(mCategory,Consts.Month.december);
////                mSelectMonth = Consts.Month.december;
////                break;
////        }
//        mSelectMonth = month;
//        mEventsModel.getEventsFrom(mCategory, month);
//    }

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
