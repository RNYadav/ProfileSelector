package com.example.profileselector.network;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService {

    @GET("/api?results=10")
    Call<JsonElement> getRandomUser();

    @GET("/api?results=10")
    Call<JsonElement> getRandomUser(@Query("gender") String gender);

}
