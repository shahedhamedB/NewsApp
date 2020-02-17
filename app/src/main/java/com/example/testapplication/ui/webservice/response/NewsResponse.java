package com.example.testapplication.ui.webservice.response;

import com.example.testapplication.ui.entity.News;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResponse {
    @SerializedName("section_info")
    @Expose
    private List<News> news = null;


    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }
}
