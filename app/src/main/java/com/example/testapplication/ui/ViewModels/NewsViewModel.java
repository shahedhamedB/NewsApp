package com.example.testapplication.ui.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.testapplication.ui.Reposatiry.CategoriesRepo;
import com.example.testapplication.ui.Utitle.Resource;
import com.example.testapplication.ui.entity.News;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {



    private CategoriesRepo categoriesRepo;


    public NewsViewModel(@NonNull Application application) {
        super(application);
        categoriesRepo = CategoriesRepo.getInstance(application) ;
    }


    public LiveData<Resource<List<News>>> getCategoryApi() {

        return categoriesRepo.getCategoriesApi();

    }

}
