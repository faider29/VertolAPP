package com.andrienkom.vertolapp.mvvm.viewModels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.andrienkom.vertolapp.entities.Articles;
import com.andrienkom.vertolapp.interfaces.ArticlesModelListener;
import com.andrienkom.vertolapp.mvvm.models.ArticlesModel;

import java.util.List;

public class ArticlesViewModel extends ViewModel implements ArticlesModelListener {

    private ArticlesModel mArticlesModel;

    public ArticlesViewModel(){
        mArticlesModel = new ArticlesModel();
        mArticlesModel.addListener(this);
        mIsLoad.postValue(true);
        mArticlesModel.start();
    }


    private MutableLiveData<List<Articles>> mArticles = new MutableLiveData<>();
    private MutableLiveData<String> mError = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsLoad = new MutableLiveData<>();







    @Override
    public void articlesListLoad(List<Articles> entityArticles) {
        mIsLoad.postValue(false);
        mArticles.postValue(entityArticles);

    }

    @Override
    public void error(String errorMessage) {
        mIsLoad.postValue(false);
        mError.postValue("Внимание произошла ошибка: \n" + errorMessage);

    }
}
