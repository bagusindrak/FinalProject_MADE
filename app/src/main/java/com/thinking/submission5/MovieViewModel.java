package com.thinking.submission5;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.thinking.submission5.database.DbProvider;
import com.thinking.submission5.database.MovieDao;
import com.thinking.submission5.entity.Movie;
import com.thinking.submission5.entity.MovieRespone;
import com.thinking.submission5.entity.Tv;
import com.thinking.submission5.entity.TvRespone;
import com.thinking.submission5.generator.ServiceGenerator;
import com.thinking.submission5.service.MovieSevice;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.thinking.submission5.Constant.API_KEY;

public class MovieViewModel extends ViewModel {
   private MutableLiveData<ArrayList<Movie>> listMovies = new MutableLiveData<>();
   private MovieSevice movieSevice = ServiceGenerator.createService(MovieSevice.class);
   private Movie movie;
   private MovieDao daoMovie;

   public void setDao(Context context) {
      this.daoMovie = DbProvider.getInstance(context).movieDao();
   }


   public void setMovieAPI() {
      Call<MovieRespone> call = movieSevice.getMoviePopular(API_KEY, "en-US", "1");
      call.enqueue(new Callback<MovieRespone>() {
         @Override
         public void onResponse(@NotNull Call<MovieRespone> call, @NotNull Response<MovieRespone> response) {
            ArrayList<Movie> listItems = new ArrayList<>();
            MovieRespone movieRespone = response.body();
            assert movieRespone != null;
            if (response.code() == 200) {
               List<Movie> list = movieRespone.getResults();
               for (int i = 0; i < list.size(); i++) {
                  Movie index = list.get(i);
                  Movie movie = new Movie();
                  movie.setId(index.getId());
                  movie.setTitle(index.getTitle());
                  movie.setOverview(index.getOverview());
                  movie.setPosterPath(index.getPosterPath());
                  listItems.add(movie);
               }
               listMovies.postValue(listItems);
               Log.e("setMovieAPISize", String.valueOf(list.size()));
            }
         }

         @Override
         public void onFailure(@NotNull Call<MovieRespone> call, @NotNull Throwable t) {
            Log.e("setMovieAPI", Objects.requireNonNull(t.getMessage()));
         }
      });
   }

   public void setTvShowAPI() {
      Call<TvRespone> call = movieSevice.getTvPopular(API_KEY, "en-US", "1");
      call.enqueue(new Callback<TvRespone>() {
         @Override
         public void onResponse(@NotNull Call<TvRespone> call, @NotNull Response<TvRespone> response) {
            ArrayList<Movie> listItems = new ArrayList<>();
            TvRespone movieRespone = response.body();
            assert movieRespone != null;
            if (response.code() == 200) {
               List<Tv> list = movieRespone.getResults();
               for (int i = 0; i < list.size(); i++) {
                  Tv index = list.get(i);
                  Movie movie = new Movie();
                  movie.setId(index.getId());
                  movie.setTitle(index.getName());
                  movie.setOverview(index.getOverview());
                  movie.setPosterPath(index.getPosterPath());
                  listItems.add(movie);
               }
               listMovies.postValue(listItems);
               Log.e("setTvAPISize", String.valueOf(list.size()));
            }
         }

         @Override
         public void onFailure(@NotNull Call<TvRespone> call, @NotNull Throwable t) {
            Log.e("setTvAPI", Objects.requireNonNull(t.getMessage()));
         }
      });
   }

   public LiveData<List<Movie>> getMovieFav() {
      return daoMovie.getAll();
   }

   public LiveData<List<Movie>> getTvFav() {
      return daoMovie.getAllTv();
   }

   public LiveData<ArrayList<Movie>> getMovies() {
      return listMovies;
   }

   public void setItemMovie(Movie movie) {
      this.movie = movie;
   }

   private Movie makeMovie() {
      Movie m = new Movie();
      m.setId(movie.getId());
      m.setTitle(movie.getTitle());
      m.setOverview(movie.getOverview());
      m.setPosterPath(movie.getPosterPath());
      return m;
   }
   private Tv makeTv() {
      Tv m = new Tv();
      m.setId(movie.getId());
      m.setName(movie.getTitle());
      m.setOverview(movie.getOverview());
      m.setPosterPath(movie.getPosterPath());
      return m;
   }

   public void insertMovie() {
      this.daoMovie.insertAll(this.makeMovie());
   }

   public void insertTv() {
      this.daoMovie.insertAllTv(this.makeTv());
   }

   public boolean isFavMovie() {
      return this.daoMovie.findById(this.movie.getId()) != null;
   }

   public boolean isFavTv() {
      return this.daoMovie.findByIdTv(this.movie.getId()) != null;
   }

   public void deleteMovieFav() {
      this.daoMovie.delete(this.movie);
   }

   public void deleteTvFav() {
      Tv tv = new Tv();
      tv.setId(this.movie.getId());
      this.daoMovie.deleteTv(tv);
   }

}

