package com.andrienkom.vertolapp.interfaces;


import com.andrienkom.vertolapp.entities.Other;
import com.google.gson.JsonObject;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api  {

    /**
     * Получение всей инфы
     * @return
     */

//    @POST ("GetNews?category=Category&month=Month")
    @POST ("GetNews?")
    @FormUrlEncoded
    Call<JsonObject> getNewsUser(
        @Field( "category") String category,
        @Field( "month") String month
    );

    @GET("GetDiscounts")
    Call<JsonObject> getSoldUser();


    @GET("GetEvents")
    Call<JsonObject> getEventsUser();


    /**
     * Статическая информация
     * @param category
     * @return
     */

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
