package com.andrienkom.vertolapp.entities;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Issues implements Serializable {


    public static final String TAG = Issues.class.getSimpleName();
    private String id;

    private String name;

    public  Issues(){

    }

    public int getId() {
        return Integer.valueOf(id);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static List<Issues> getIssuesFromJson(JsonObject response){
        List<Issues> issuesList = new ArrayList<>();
        Gson gson = new Gson();
        JsonArray array = response.getAsJsonArray("result");
        Log.d(TAG, "getIssuesFromJson: " + response.getAsJsonArray("result"));
        for (int i=0; i< array.size();i++){
            Issues issues = gson.fromJson(array.get(i),Issues.class);
            issuesList.add(issues);
        }
        return issuesList;
    }





    @Override
    public String toString() {
        return "id: " + id + "name: " + name;
    }
}
