package com.earthsilen.depthfirsttraineeproject.ApiService;

import com.earthsilen.depthfirsttraineeproject.Models.DoListModels.DoListModel;
import com.earthsilen.depthfirsttraineeproject.Models.NewsModels.Example;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServiceDoList {

    //List<Example> test = new ArrayList<>();

    @POST("mobile")
    Call<DoListModel> resposDolist(@Query("serviceName") String type, @Query("ORG_CODE") int orgCode);
}