package com.andrienkom.vertolapp.network;


import com.andrienkom.vertolapp.entities.News;
import com.andrienkom.vertolapp.interfaces.Api;
import com.andrienkom.vertolapp.utility.Consts;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * приватный конструктор чтобы не могли создать извне обект
 * этого класса
 */
public class NetworkRepository {

    private static NetworkRepository sRepository;

    private static final String TAG = NetworkRepository.class.getSimpleName();

    private NetworkRepository() {
    }

    /**
     * через этот метод получаем обьект класса
     * @return
     */
    public static NetworkRepository getInstance() {
        if (sRepository == null) sRepository = new NetworkRepository(); // если обьект еще не создан то создаем
        return sRepository; // возвращаем обьект
    }


    /**
     * retrofit News
     * @param callback
     */
   public void getAllNews(Callback<JsonObject> callback) {
       Retrofit.Builder builder = new Retrofit.Builder()
               .baseUrl(Consts.BASE_URL)
               .addConverterFactory(GsonConverterFactory.create());
       Retrofit retrofit = builder.build();

       final Api api = retrofit.create(Api.class);

       Call<JsonObject> j = api.getNewsUser(Consts.Category.all.toString(), "null");
       j.enqueue(callback);
   }

    /**
     * retrofit FSK News
     * @param callback
     */
    public void getFSKNews(Callback<JsonObject> callback) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Consts.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        final Api api = retrofit.create(Api.class);

        Call<JsonObject> j = api.getNewsFromFSK(Consts.Category.fsk.toString(), "null");
        j.enqueue(callback);
    }

    public void getAFromCategoryNews(Callback<JsonObject> callback, String category) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Consts.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        final Api api = retrofit.create(Api.class);

        Call<JsonObject> j = api.getNewsUser("category", "null");
        j.enqueue(callback);
    }

    /**
     * Retrofit Sold
     * @param callback
     */
    public void getSold(Callback<JsonObject> callback) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Consts.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        final Api api = retrofit.create(Api.class);

        Call<JsonObject> j = api.getSoldUser();
        j.enqueue(callback);
    }

    /**
     * retrofit Events
     */
    public void getEvents(Callback<JsonObject> callback) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Consts.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        final Api api = retrofit.create(Api.class);

        Call<JsonObject> j = api.getEventsUser();
        j.enqueue(callback);
    }

}