//package com.andrienkom.vertolapp.mvvm.models;
//
//import android.util.Log;
//
//import com.andrienkom.vertolapp.entities.Issues;
//import com.andrienkom.vertolapp.interfaces.IssuesModelListener;
//import com.andrienkom.vertolapp.network.NetworkRepository;
//import com.google.gson.JsonObject;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class IssuesModel {
//
//    private Callback<JsonObject> mCallback;
//
//    private List<IssuesModelListener> mListeners = new ArrayList<>();
//
//    public IssuesModel(){
//        initCallback();
//    }
//
//    public void start(){
//        NetworkRepository.getInstance().getIssues(mCallback);
//    }
//
//    public void addListener(IssuesModelListener listener){
//        mListeners.add(listener);
//    }
//    public void removeListener(IssuesModelListener listener){
//        mListeners.remove(listener);
//    }
//
//    public void initCallback(){
//        mCallback = new Callback<JsonObject>() {
//            @Override
//            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                for (IssuesModelListener listener: mListeners){
//                    listener.issuesListLoad(Issues.getIssuesFromJson(response.body()));
//
//
//                    Log.d("onResponseIssues ", "onResponseIssues: " + response.body());
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<JsonObject> call, Throwable t) {
//                t.printStackTrace();
//                for (IssuesModelListener listener: mListeners){
//                    listener.error(t.getMessage());
//                }
//            }
//        };
//    }
//
//
//}