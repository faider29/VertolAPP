package com.andrienkom.vertolapp.entities;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class News implements Serializable {


    private String title;


    private String text;


    private String img;


    private String date;

    public News() {}


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Да, сущность не должна уметь себя парсить
     * но я считаю что должна.
     * @param response - на вход получаем сырой JSON
     * @return - если удачно распарсили, то возвращаем список обьектов
     * @throws JSONException - если словили exception, то прокидываем его вызывающему методу
     */
    public static List<News> getNewsFromJson(JsonObject response) {
        List<News> newsList = new ArrayList<>();
        Gson gson = new Gson();
        JsonArray array = response.getAsJsonArray("result");

        for (int i = 0; i < array.size(); i++) {
            News news = gson.fromJson(array.get(i), News.class);
            newsList.add(news);
        }

        return newsList;
    }

    @Override
    public String toString() {
        return  " title: " + title + " text: " + text + " date: " + date;
    }
}
