package com.example.testapplication.ui.Reposatiry;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.testapplication.ui.AppExecutors;
import com.example.testapplication.ui.Utitle.NetworkBoundResource;
import com.example.testapplication.ui.Utitle.Resource;
import com.example.testapplication.ui.entity.News;
import com.example.testapplication.ui.persistence.CategoriesDB;

import com.example.testapplication.ui.persistence.CategoriesDao;
import com.example.testapplication.ui.webservice.WepServiceClient;
import com.example.testapplication.ui.webservice.response.ApiResponse;
import com.example.testapplication.ui.webservice.response.NewsResponse;

import java.util.List;

public class CategoriesRepo {
    private CategoriesDao categoriesDao;
    private static CategoriesRepo categoriesRepo = null;


    public CategoriesRepo(Context context) {
        categoriesDao = CategoriesDB.getInstance(context).categoriesDao();
    }

    public static CategoriesRepo getInstance(Application application) {
        if (categoriesRepo == null) {
            categoriesRepo = new CategoriesRepo(application);
        }
        return categoriesRepo;
    }


    public LiveData<Resource<List<News>>> getCategoriesApi() {
        return new NetworkBoundResource<List<News>, NewsResponse>(AppExecutors.getInstance()) {

            @Override
            protected void saveCallResult(@NonNull NewsResponse item) {
                if (item.getNews() != null) {

                    Log.e("saveCallResult" , item.getNews().get(1).getStrName()) ;
                    News[] categories = new News[item.getNews().size()];
                    int index = 0;
                    for (long rowId : categoriesDao.InsertCategories(item.getNews().toArray(categories))) {


                        if (rowId == -1) {

                            categoriesDao.UpdateCategories(categories[index]);
                        }
                        index++;
                    }
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<News> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<News>> loadFromDb() {
                return categoriesDao.getAllCategories();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<NewsResponse>> createCall() {
                return WepServiceClient.getRecipeApi().getNews(1);
            }
        }.getAsLiveData();
    }
}
