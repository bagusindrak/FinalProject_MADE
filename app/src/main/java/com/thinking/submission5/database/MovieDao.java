package com.thinking.submission5.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.thinking.submission5.entity.Movie;
import com.thinking.submission5.entity.Tv;

import java.util.List;

@Dao
public interface MovieDao {
   @Query("SELECT * FROM Movie")
   LiveData<List<Movie>> getAll();

   @Query("SELECT * FROM Movie WHERE id = :id")
   Movie findById(Long id);

   @Insert
   void insertAll(Movie... movies);

   @Delete
   void delete(Movie movie);

   @Query("SELECT * FROM Tv")
   LiveData<List<Movie>> getAllTv();

   @Query("SELECT * FROM Tv WHERE id = :id")
   Movie findByIdTv(Long id);

   @Insert
   void insertAllTv(Tv... movies);

   @Delete
   void deleteTv(Tv tv);
}
