package com.andrienkom.vertolapp.interfaces;

import com.andrienkom.vertolapp.entities.Articles;

import java.util.List;

public interface ArticlesModelListener {

    void articlesListLoad (List<Articles> entityArticles);

    void  error(String errorMessage);
}
