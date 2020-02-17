package com.example.testapplication.ui.persistence;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.testapplication.ui.entity.News;

@Database(entities = {News.class}, version = 1,exportSchema = false)
public abstract class CategoriesDB extends RoomDatabase {


    public abstract CategoriesDao categoriesDao();

    private static final String DATABASE_NAME = "news_db";

    private static CategoriesDB categoriesDB = null;

    public static CategoriesDB getInstance(Context context) {
        if (categoriesDB == null) {
            categoriesDB = Room.databaseBuilder(
                    context.getApplicationContext(),
                    CategoriesDB.class, DATABASE_NAME)
                    .build();
        }
        return categoriesDB;
    }


}
