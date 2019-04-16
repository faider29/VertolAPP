package com.andrienkom.vertolapp.entities;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Articles implements Serializable {

    public Articles(){}

    private List<Articles> mArticlesList;

    private String title;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static List<Articles> getArticlesFromJson(JsonObject response){
        List<Articles> articlesList = new ArrayList<>();
        Gson gson = new Gson();
        JsonArray jsonArray = response.getAsJsonArray("result");
        for (int i = 0; i<jsonArray.size();i++){
            Articles articles = gson.fromJson(jsonArray.get(i),Articles.class);
            articlesList.add(articles);
        }

        return articlesList;
    }


    @Override
    public String toString() {
        return "title: " +title;
    }
}
