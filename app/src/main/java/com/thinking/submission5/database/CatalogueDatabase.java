package com.thinking.submission5.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.thinking.submission5.entity.Movie;
import com.thinking.submission5.entity.Tv;

@Database(entities = {Movie.class, Tv.class}, version = 1, exportSchema = false)
public abstract class CatalogueDatabase extends RoomDatabase {
   public abstract MovieDao movieDao();
}
