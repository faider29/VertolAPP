package com.andrienkom.vertolapp.mvvm.viewModels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.andrienkom.vertolapp.entities.Issues;
import com.andrienkom.vertolapp.interfaces.IssuesModelListener;
import com.andrienkom.vertolapp.mvvm.models.IssuesModel;

import java.util.List;

public class IssuesViewModel extends ViewModel implements IssuesModelListener {

    private IssuesModel mIssuesModel;

    private MutableLiveData<List<Issues>> mIssues = new MutableLiveData<>();
    private MutableLiveData<String> mError = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsLoad= new MutableLiveData<>();

    public IssuesViewModel(){
        mIssuesModel = new IssuesModel();
        mIssuesModel.addListener(this);
        mIsLoad.postValue(true);
        mIssuesModel.start();
    }

    public void issuesListLoad(List<Issues> issues){
        mIsLoad.postValue(false);
        mIssues.postValue(issues);
    }


    @Override
    protected void onCleared() {
        mIssuesModel.removeListener(this);
        mIssuesModel = null;
        super.onCleared();
    }



    @Override
    public void error(String errorMessage) {
        mIsLoad.postValue(false);
        mError.postValue("Внимание произошла ошибка: \n" + errorMessage);
    }

    public MutableLiveData<List<Issues>> getIssues(){
        return mIssues;
    }
    public MutableLiveData<String> getError(){
        return mError;
    }
    public MutableLiveData<Boolean> getIsLoad(){
        return mIsLoad;
    }
}
