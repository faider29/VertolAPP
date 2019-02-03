package com.andrienkom.vertolapp.mvvm.interfaces;

import com.andrienkom.vertolapp.entities.EntityNews;

import java.util.List;

public interface MainModelListener {
    void articlesListLoad(List<EntityNews> entityNews);

    void error(String errorMessage);

}
