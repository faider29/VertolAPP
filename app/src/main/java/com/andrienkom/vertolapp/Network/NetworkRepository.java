package com.andrienkom.vertolapp.Network;


import android.util.Log;

import com.andrienkom.vertolapp.Application;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

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

    public void get(String url, Response.Listener<JSONArray> callBack, Response.ErrorListener errorCallback) {
        Log.d(TAG, "get: url");
        Application.getInstance().addToRequestQueue(new JsonArrayRequest(Request.Method.GET,url, callBack, errorCallback));
    }


}
