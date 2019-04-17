package com.andrienkom.vertolapp.network;



import com.andrienkom.vertolapp.interfaces.Api;
import com.andrienkom.vertolapp.utility.Consts;
import com.google.gson.JsonObject;

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
//       Call<JsonObject> j = api.getNewsUser(Consts.Category.all.toString(), "null");
//   j.enqueue(callback);
   }


    /**
     * retrofit NewsCategory
     */
    public void getNewsFromCategory(Callback<JsonObject> callback, Consts.Category category) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Consts.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        final Api api = retrofit.create(Api.class);

        Call<JsonObject> j = api.getNews(category.toString());
        j.enqueue(callback);
    }

    /**
     * retrofit Calendar
     * @param callback
     */
    public void getEventsFrom(Callback<JsonObject> callback, Consts.Category category, Consts.Month month) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Consts.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        final Api api = retrofit.create(Api.class);
        Call<JsonObject> j = api.getEventsUser(category.toString(), month.toString());
        j.enqueue(callback);
    }



    /**
     * Retrofit Service
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
     * Retrofit on Issues
     * @param callback
     */
    public void getIssues(Callback<JsonObject> callback){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Consts.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofitIssues = builder.build();

        final Api api = retrofitIssues.create(Api.class);

        Call<JsonObject> j = api.getIssues();
        j.enqueue(callback);

    }

    /**
     * Retrofit on Articles
     */

    public void getArticles(Callback<JsonObject> callback){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Consts.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofitArticles = builder.build();

        final Api api = retrofitArticles.create(Api.class);

        Call<JsonObject> j = api.getArticles();
        j.enqueue(callback);
    }


}