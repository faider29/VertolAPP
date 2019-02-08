package com.andrienkom.vertolapp.interfaces;

import com.andrienkom.vertolapp.entities.News;
import com.google.gson.JsonObject;


import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api  {
    @POST ("GetNews?category=all&month=null")
    @FormUrlEncoded
    Call<JsonObject> getNewsUser(
        @Field( "Category") String category,
        @Field( "Month") String month
    );

//        @GET("GetDiscounts")
//        @FormUrlEncoded
//    Call<JsonObject> getSoldUser(
//    );

    @GET("GetDiscounts")
    Call<JsonObject> getSoldUser();




    @GET("GetEvents")
    Call<JsonObject> getEventsUser();


}
