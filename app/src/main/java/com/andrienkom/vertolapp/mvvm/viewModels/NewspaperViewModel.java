package com.andrienkom.vertolapp.mvvm.viewModels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.andrienkom.vertolapp.entities.Articles;
import com.andrienkom.vertolapp.entities.Issues;
import com.andrienkom.vertolapp.interfaces.ArticlesModelListener;
import com.andrienkom.vertolapp.interfaces.IssuesModelListener;
import com.andrienkom.vertolapp.mvvm.models.NewspaperModel;

import java.util.List;

/**
 * Created by Maxim Andrienko
 * 5/26/19
 */
public class NewspaperViewModel extends ViewModel
                                 implements IssuesModelListener,
                                    ArticlesModelListener {

    private NewspaperModel mNewspaperModel;

    private MutableLiveData<List<Issues>> mIssues = new MutableLiveData<>();
    private MutableLiveData<List<Articles>> mArticles = new MutableLiveData<>();
    private MutableLiveData<String> mError = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsLoad = new MutableLiveData<>();


    public NewspaperViewModel(){
        mNewspaperModel = new NewspaperModel();
        mNewspaperModel.addListenerIssues(this);
        mNewspaperModel.addListenerArticles(this);
        mIsLoad.postValue(true);
        mNewspaperModel.start();
    }

    public void getIssuesFromId(Issues issues) {
        mNewspaperModel.getArticlesFromIssuesID(issues.getId());
    }

    @Override
    public void articlesListLoad(List<Articles> entityArticles) {
        mIsLoad.postValue(false);
        mArticles.postValue(entityArticles);

    }

    @Override
    public void issuesListLoad(List<Issues> entityIssues) {
        mIsLoad.postValue(false);
        mIssues.postValue(entityIssues);
    }

    @Override
    public void error(String errorMessage) {
        mIsLoad.postValue(false);
        mError.postValue("Ошибка: " + errorMessage);
    }

    @Override
    protected void onCleared() {
        mNewspaperModel.removeListenerArticles(this);
        mNewspaperModel.removeListenerIssues(this);
        mNewspaperModel = null;
        super.onCleared();
    }

    public MutableLiveData<List<Issues>> getIssues(){
        return mIssues;
    }
    public MutableLiveData<List<Articles>> getArticles(){
        return mArticles;
    }
    public MutableLiveData<String> getError(){
        return mError;
    }
    public MutableLiveData<Boolean> getIsLoad(){
        return mIsLoad;
    }
}
