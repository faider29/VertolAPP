package com.andrienkom.vertolapp.entities;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Articles implements Serializable {

    public Articles(){}

    private String id;

    private String title;

    private String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
            Log.d("getArticlesFromJson", "getArticlesFromJson: " + response.getAsJsonArray("result"));

        }

        return articlesList;
    }


    @Override
    public String toString() {
        return "id: " + id + "title: " +title;
    }


    public String getText() {
        return text;
    }
}
