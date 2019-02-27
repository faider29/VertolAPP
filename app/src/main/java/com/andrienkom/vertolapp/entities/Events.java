package com.andrienkom.vertolapp.entities;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Events implements Serializable {

    private String title;

    private String text;

    private String img;

    private String date;

    public Events() {
    }


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



    public static List<Events> getEventsFromJson(JsonObject response) throws ClassCastException{
        List<Events> eventsList = new ArrayList<>();
        Gson gson = new Gson();
        JsonArray array = response.getAsJsonArray("result");
        for (int i = 0; i < array.size(); i++) {
            Events events = gson.fromJson(array.get(i), Events.class);
            eventsList.add(events);
        }

        return eventsList;
    }

    @Override
    public String toString() {
        return " title: " + title + " text: " + text + " date: " + date;
    }
}
