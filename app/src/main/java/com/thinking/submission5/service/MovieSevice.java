package com.thinking.submission5.service;

import com.thinking.submission5.entity.MovieRespone;
import com.thinking.submission5.entity.Tv;
import com.thinking.submission5.entity.TvRespone;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieSevice {

   @GET("/3/movie/popular")
   Call<MovieRespone> getMoviePopular(@Query("api_key") String api_key,
                                      @Query("language") String language, @Query("page") String page);

   @GET("/3/tv/popular")
   Call<TvRespone> getTvPopular(@Query("api_key") String api_key,
                         @Query("language") String language, @Query("page") String page);

   @GET("movie/now_playing")
   Call<MovieRespone> getNowPlayingMovie(@Query("api_key") String apikey);

//   @GET("tv/airing_today")
//   Call<TvRespone> getTvAiringToday(@Query("api_key") String apikey);

//   @GET("search/multi")
//   Call<SearchRespone> getMovieSearch(@Query("query") String key, @Query("api_key") String apikey);

   @GET("movie/upcoming")
   Call<MovieRespone> getUpComingMovie(@Query("api_key") String apikey);

}
