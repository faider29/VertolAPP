package com.andrienkom.vertolapp.interfaces;

import com.andrienkom.vertolapp.entities.News;

import java.util.List;

public interface MainModelListener {
    void articlesListLoad(List<News> entityNews);

    void error(String errorMessage);

}

