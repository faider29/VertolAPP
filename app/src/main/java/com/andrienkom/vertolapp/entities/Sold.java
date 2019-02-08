package com.andrienkom.vertolapp.entities;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class Sold {

    private String title;

    private String text;

    private String img;

    private  String date;

    public Sold(){}


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

    public static List<Sold> getSoldFromJson(JsonObject response) {
        List<Sold> soldList = new ArrayList<>();
        Gson gson = new Gson();
        JsonArray array = response.getAsJsonArray("result");
        for (int i = 0; i < array.size(); i++) {
            Sold sold = gson.fromJson(array.get(i), Sold.class);
            soldList.add(sold);
        }

        return soldList;
    }

    @Override
    public String toString() {
        return  " title: " + title + " text: " + text + " date: " + date;
    }
}
