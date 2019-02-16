package com.andrienkom.vertolapp.mvvm.viewModels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.andrienkom.vertolapp.entities.News;
import com.andrienkom.vertolapp.interfaces.MainModelListener;
import com.andrienkom.vertolapp.mvvm.models.NewsModel;
import com.andrienkom.vertolapp.utility.Consts;

import java.util.List;

public class NewsViewModel extends ViewModel implements MainModelListener {

    private NewsModel mModel;

    private MutableLiveData<List<News>> mArticles = new MutableLiveData<>();
    private MutableLiveData<String> mError = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsLoad = new MutableLiveData<>();


    public NewsViewModel() {
        mModel = new NewsModel();
        mModel.addListener(this);
        mIsLoad.postValue(true);
        mModel.start();
    }

    @Override
    public void articlesListLoad(List<News> news) {
        mIsLoad.postValue(false);
        mArticles.postValue(news);
    }

    @Override
    public void error(String errorMessage) {
        mIsLoad.postValue(false);
        mError.postValue("Внимание произошла ошибка:\n" + errorMessage);
    }

    /**
     * Вызывается перед уничтожением ViewModel
     * Перед уничтожением модели нужно отписаться
     * чтобы не словить утечку памяти
     */
    @Override
    protected void onCleared() {
        mModel.removeListener(this);
        mModel = null;
        super.onCleared();
    }

    public void getNewsFrom(Consts.Category category, String month) {
        mModel.getNewsFrom(category, month);
    }


    public MutableLiveData<List<News>> getArticles() {
        return mArticles;
    }


    public MutableLiveData<String> getError() {
        return mError;
    }


    public MutableLiveData<Boolean> getIsLoad() {
        return mIsLoad;
    }
}

