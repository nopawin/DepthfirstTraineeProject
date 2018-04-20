package com.earthsilen.depthfirsttraineeproject.HTTPManager;

import com.earthsilen.depthfirsttraineeproject.ApiService.ApiServiceGooglePlaceID;
import com.earthsilen.depthfirsttraineeproject.ApiService.ApiServiceNews;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManagerGooglePlaceID {

    private static HttpManagerGooglePlaceID instances;

    public static HttpManagerGooglePlaceID getInstance() {
        if (instances == null)
            instances = new HttpManagerGooglePlaceID();
        return instances;
    }
    private ApiServiceGooglePlaceID service;

    private HttpManagerGooglePlaceID() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/place/nearbysearch/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ApiServiceGooglePlaceID.class);
    }

    public  ApiServiceGooglePlaceID getService() {
        return service;
    }
}