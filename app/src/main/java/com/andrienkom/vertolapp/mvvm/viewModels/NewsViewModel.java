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

    private Consts.Category mCategory = Consts.Category.all;
    private Consts.Month mSelectMonth = Consts.Month.all;

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

    public void getNewsFrom(int position) {
        mIsLoad.postValue(true);
        switch (position) {
            case 0:
                mModel.getNewsFrom(Consts.Category.all, mSelectMonth);
                mCategory = Consts.Category.all;
                break;
            case 1:
                mModel.getNewsFrom(Consts.Category.fsk, mSelectMonth);
                mCategory = Consts.Category.fsk;
                break;
            case 2:
                mModel.getNewsFrom(Consts.Category.sdk, mSelectMonth);
                mCategory = Consts.Category.sdk;
                break;
            case 3:
                mModel.getNewsFrom(Consts.Category.ry, mSelectMonth);
                mCategory = Consts.Category.ry;
                break;
        }

    }

    public void getNewsFromMonth(int position) {
        mIsLoad.postValue(true);
       switch (position){
           case 0:
               mModel.getNewsFrom(mCategory,Consts.Month.all);
               mSelectMonth = Consts.Month.all;
               break;
           case 1:
               mModel.getNewsFrom(mCategory,Consts.Month.january);
               mSelectMonth = Consts.Month.january;
               break;
           case 2:
               mModel.getNewsFrom(mCategory,Consts.Month.february);
               mSelectMonth = Consts.Month.february;
               break;
           case 3:
               mModel.getNewsFrom(mCategory,Consts.Month.march);
               mSelectMonth = Consts.Month.march;
               break;
           case 4:
               mModel.getNewsFrom(mCategory,Consts.Month.april);
               mSelectMonth = Consts.Month.april;
               break;
           case 5:
               mModel.getNewsFrom(mCategory,Consts.Month.may);
               mSelectMonth = Consts.Month.may;
               break;
           case 6:
               mModel.getNewsFrom(mCategory,Consts.Month.june);
               mSelectMonth = Consts.Month.june;
               break;
           case 7:
               mModel.getNewsFrom(mCategory,Consts.Month.july);
               mSelectMonth = Consts.Month.july;
               break;
           case 8:
               mModel.getNewsFrom(mCategory,Consts.Month.august);
               mSelectMonth = Consts.Month.august;
               break;
           case 9:
               mModel.getNewsFrom(mCategory,Consts.Month.september);
               mSelectMonth = Consts.Month.september;
               break;
           case 10:
               mModel.getNewsFrom(mCategory,Consts.Month.october);
               mSelectMonth = Consts.Month.october;
               break;
           case 11:
               mModel.getNewsFrom(mCategory,Consts.Month.december);
               mSelectMonth = Consts.Month.december;
               break;
       }
    }

  /*  public void getNewsFromMonth(String month) {
        mMonth = month;
        mModel.getNewsFrom(mCategory, month);
    }*/




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

