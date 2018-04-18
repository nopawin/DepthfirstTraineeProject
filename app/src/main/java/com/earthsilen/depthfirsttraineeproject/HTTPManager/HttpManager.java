package com.earthsilen.depthfirsttraineeproject.HTTPManager;

import com.earthsilen.depthfirsttraineeproject.ApiService.ApiServiceNews;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {

    private static HttpManager instances;

    public static HttpManager getInstance() {
        if (instances == null)
            instances = new HttpManager();
        return instances;
    }
    private ApiServiceNews service;

    private HttpManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://122.155.13.198/MOT_DK/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ApiServiceNews.class);
    }

    public  ApiServiceNews getService() {
        return service;
    }
}