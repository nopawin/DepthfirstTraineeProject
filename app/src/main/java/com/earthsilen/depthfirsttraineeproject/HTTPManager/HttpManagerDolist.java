package com.earthsilen.depthfirsttraineeproject.HTTPManager;

import com.earthsilen.depthfirsttraineeproject.ApiService.ApiServiceDoList;
import com.earthsilen.depthfirsttraineeproject.ApiService.ApiServiceNews;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManagerDolist {

    private static HttpManagerDolist instances;

    public static HttpManagerDolist getInstance() {
        if (instances == null)
            instances = new HttpManagerDolist();
        return instances;
    }
    private ApiServiceDoList service;

    private HttpManagerDolist() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://eservice.dla.go.th/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ApiServiceDoList.class);
    }

    public  ApiServiceDoList getService() {
        return service;
    }
}