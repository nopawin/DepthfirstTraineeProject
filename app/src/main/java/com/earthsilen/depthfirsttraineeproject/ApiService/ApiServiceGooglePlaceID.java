package com.earthsilen.depthfirsttraineeproject.ApiService;

import com.earthsilen.depthfirsttraineeproject.Models.GooglePlaceIdModel.PlaceIDModel;
import com.earthsilen.depthfirsttraineeproject.Models.NewsModels.Example;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServiceGooglePlaceID {

    //List<Example> test = new ArrayList<>();

    @POST("json")
    Call<PlaceIDModel> repos(@Query("location") String location, @Query("radius") int radius, @Query("key") String key);

}