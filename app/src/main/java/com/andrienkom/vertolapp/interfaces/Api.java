package com.andrienkom.vertolapp.interfaces;


import com.andrienkom.vertolapp.entities.Other;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api  {

    /**
     * Получение Событий календаря
     * @return
     */

    @POST ("GetEvents?")
    @FormUrlEncoded
    Call<JsonObject> getEventsUser(
        @Field( "category") String category,
        @Field( "month") String month
    );

    /**
     * Получения Услуг
     * @return
     */
    @GET("GetDiscounts")
    Call<JsonObject> getSoldUser();

    /**
     * Получение Новостей
     * @param category
     * @return
     */

    @POST("GetNews?")
    @FormUrlEncoded
    Call<JsonObject> getNews(
            @Field("category") String category
    );


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

    /**
     * Список выпусков
     * @return
     */
    @GET ("GetIssues")
    Call<JsonObject> getIssues();


    /**
     * Список новостей по выпуску
     */
    @POST("GetArticles?id=")
    @FormUrlEncoded
    Call<JsonObject> getArticles(
            @Field("id") String id
    );

}
