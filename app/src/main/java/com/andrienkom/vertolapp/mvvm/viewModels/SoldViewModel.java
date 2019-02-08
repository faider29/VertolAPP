package com.andrienkom.vertolapp.mvvm.viewModels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.andrienkom.vertolapp.entities.Sold;
import com.andrienkom.vertolapp.interfaces.SoldModelListener;
import com.andrienkom.vertolapp.mvvm.models.SoldModel;

import java.util.List;

public class SoldViewModel extends ViewModel implements SoldModelListener {

    private SoldModel mModel;


    private MutableLiveData<List<Sold>> mSolds = new MutableLiveData<>();
    private MutableLiveData<String> mError = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsLoad = new MutableLiveData<>();

    public SoldViewModel(){
        mModel = new SoldModel();
        mModel.addListener(this);
        mIsLoad.postValue(true);
        mModel.start();
    }


    @Override
    public void soldListLoad(List<Sold> sold) {
        mIsLoad.postValue(false);
        mSolds.postValue(sold);

    }

    @Override
    public void error(String errorMessage) {
        mIsLoad.postValue(false);
        mError.postValue("Внимание произошла ошибка: \n" + errorMessage);

    }

    @Override
    protected void onCleared() {
        mModel.removeListener(this);
        mModel = null;
        super.onCleared();
    }

    public MutableLiveData<List<Sold>> getSold() {
        return mSolds;
    }


    public MutableLiveData<String> getError() {
        return mError;
    }


    public MutableLiveData<Boolean> getIsLoad() {
        return mIsLoad;
    }
}
