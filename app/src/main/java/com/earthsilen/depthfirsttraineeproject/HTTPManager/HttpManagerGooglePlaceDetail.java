package com.earthsilen.depthfirsttraineeproject.HTTPManager;

import com.earthsilen.depthfirsttraineeproject.ApiService.ApiServiceGooglePlaceDetail;
import com.earthsilen.depthfirsttraineeproject.ApiService.ApiServiceGooglePlaceID;
import com.earthsilen.depthfirsttraineeproject.ApiService.ApiServiceNews;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManagerGooglePlaceDetail {

    private static HttpManagerGooglePlaceDetail instances;

    public static HttpManagerGooglePlaceDetail getInstance() {
        if (instances == null)
            instances = new HttpManagerGooglePlaceDetail();
        return instances;
    }
    private ApiServiceGooglePlaceDetail service;

    private HttpManagerGooglePlaceDetail() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/place/details/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ApiServiceGooglePlaceDetail.class);
    }

    public  ApiServiceGooglePlaceDetail getService() {
        return service;
    }
}