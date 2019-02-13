package com.andrienkom.vertolapp.interfaces;


import com.andrienkom.vertolapp.entities.Other;
import com.google.gson.JsonObject;


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



    @POST ("GetNews?category=FSK&month=null")
    @FormUrlEncoded
    Call<JsonObject> getNewsFromFSK(
            @Field("Category") String category,
            @Field("Month") String month
    );



    @GET("GetDiscounts")
    Call<JsonObject> getSoldUser();




    @GET("GetEvents")
    Call<JsonObject> getEventsUser();

    @POST ("GetStatic?category=FSK")
    @FormUrlEncoded
    Call<Other> getFCK(
            @Field("Category") String category
    );

    @POST ("GetStatic?category=SDK")
    @FormUrlEncoded
    Call<Other> getSDK(
            @Field("Category") String category
    );

    @POST("GetStatic?category=RY")
    @FormUrlEncoded
    Call<Other> getYouth(
            @Field("Category") String category
    );


}
