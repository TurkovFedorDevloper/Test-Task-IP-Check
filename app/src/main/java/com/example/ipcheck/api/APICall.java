package com.example.ipcheck.api;

import com.example.ipcheck.model.DataModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APICall {

    @GET("check?access_key=60768222b4cb93ed5becec7a4dab1731")
    Call<DataModel> getData();

}
