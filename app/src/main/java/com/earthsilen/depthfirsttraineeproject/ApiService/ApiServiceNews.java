package com.earthsilen.depthfirsttraineeproject.ApiService;


import com.earthsilen.depthfirsttraineeproject.Models.NewsModels.Example;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServiceNews {

    //List<Example> test = new ArrayList<>();

    @POST("mobile")
    Call<Example> repos(@Query("cmd") String type);

    @POST("mobile")
    Call<Example> reposLoadMore(@Query("cmd") String type, @Query("position") int position);
}