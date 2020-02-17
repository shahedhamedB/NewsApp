package com.example.testapplication.ui.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.testapplication.ui.entity.News;

import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface CategoriesDao {

    @Query("SELECT * FROM news")
    LiveData<List<News>> getAllCategories() ;


    @Insert(onConflict = IGNORE)
    long[] InsertCategories(News ... categories);

    @Update(onConflict = REPLACE)
    void UpdateCategories(News category) ;

}
