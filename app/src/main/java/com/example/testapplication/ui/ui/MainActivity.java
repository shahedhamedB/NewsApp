package com.example.testapplication.ui.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;

import com.example.testapplication.R;
import com.example.testapplication.databinding.ActivityMainBinding;
import com.example.testapplication.ui.Utitle.Resource;
import com.example.testapplication.ui.ViewModels.NewsViewModel;
import com.example.testapplication.ui.adapter.NewsAdapter;
import com.example.testapplication.ui.adapter.Pagination;
import com.example.testapplication.ui.entity.News;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    NewsAdapter mCategories_adapter;



    ActivityMainBinding mainbinding;
    NewsViewModel mCategory_ViewModel;
    private static final int START_PAGE=1;
    private int TOTAL_PAGE=10;
    private boolean isLoading=false;
    private boolean isLastPage=false;
    private int CURANT_PAGE=START_PAGE;
    LinearLayoutManager linearLayoutManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainbinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        initViews();
        SubscripEvents();
    }
    private void initViews() {



        mCategory_ViewModel = ViewModelProviders.of(MainActivity.this).get(NewsViewModel.class);


    }

    private void SubscripEvents() {
        linearLayoutManage=new LinearLayoutManager(this);
  mainbinding.categoryRecycler.setLayoutManager(linearLayoutManage);
        mCategory_ViewModel.getCategoryApi().observe(this, new Observer<Resource<List<News>>>() {
            @Override
            public void onChanged(Resource<List<News>> listResource) {
                mCategories_adapter = new NewsAdapter(listResource.data, MainActivity.this);
                mainbinding.categoryRecycler.addOnScrollListener(new Pagination(linearLayoutManage) {
                    @Override
                    protected void loadMoreItem() {
                        isLoading=true;
                        CURANT_PAGE+=1;
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loadNextPage();

                            }
                        }, 1000);

                    }

                    @Override
                    protected int getTotalPages() {
                        return TOTAL_PAGE;
                    }

                    @Override
                    protected boolean isLastPage() {
                        return isLastPage;
                    }

                    @Override
                    protected boolean isLoading() {
                        return isLoading;
                    }
                });
                mainbinding.categoryRecycler.setAdapter(mCategories_adapter);
                if(CURANT_PAGE<=TOTAL_PAGE){
                    mCategories_adapter.addBottomItem();
                }else {
                    isLastPage=true;
                }


            }
        });
    }

    private void loadNextPage() {
        mCategory_ViewModel.getCategoryApi().observe(this, new Observer<Resource<List<News>>>() {
            @Override
            public void onChanged(Resource<List<News>> listResource) {
                mCategories_adapter.removeLastEmptyItem();
                isLoading=false;
                mCategories_adapter.addAll(listResource.data);

                if(CURANT_PAGE<=TOTAL_PAGE){
                    mCategories_adapter.addBottomItem();
                }else {
                    isLastPage=true;
                }
            }
        });

    }
}
