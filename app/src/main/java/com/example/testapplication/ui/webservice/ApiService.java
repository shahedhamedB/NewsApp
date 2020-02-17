package com.example.testapplication.ui.webservice;

import androidx.lifecycle.LiveData;

import com.example.testapplication.ui.webservice.response.ApiResponse;
import com.example.testapplication.ui.webservice.response.NewsResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("page")
    LiveData<ApiResponse<NewsResponse>> getNews(@Query("page") int page) ;
}
