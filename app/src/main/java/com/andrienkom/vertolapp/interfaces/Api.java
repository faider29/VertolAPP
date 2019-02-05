package com.andrienkom.vertolapp.interfaces;

import com.andrienkom.vertolapp.entities.News;
import com.google.gson.JsonObject;


import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api  {
    @POST ("GetNews?category=all&month=null")
    @FormUrlEncoded
    Call<JsonObject> getNewsUser(
        @Field( "Category") String category,
        @Field( "Month") String month
    );


}
