package com.zarmada.zarmadatest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIServices {

    @GET("surveys")
    public Call<List<Survey>> getSurveys();

    @POST("surveys")
    public Call<Void> addSurveys(@Body Survey body);

}
