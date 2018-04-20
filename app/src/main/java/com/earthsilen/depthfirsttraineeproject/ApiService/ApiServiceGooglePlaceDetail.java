package com.earthsilen.depthfirsttraineeproject.ApiService;

import com.earthsilen.depthfirsttraineeproject.Models.GooglePlaceDetailModel.PlaceDetailModel;
import com.earthsilen.depthfirsttraineeproject.Models.GooglePlaceIdModel.PlaceIDModel;
import com.earthsilen.depthfirsttraineeproject.Models.NewsModels.Example;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServiceGooglePlaceDetail {

    //List<Example> test = new ArrayList<>();

    @POST("json")
    Call<PlaceDetailModel> repos(@Query("placeid") String placeid, @Query("key") String key);

}