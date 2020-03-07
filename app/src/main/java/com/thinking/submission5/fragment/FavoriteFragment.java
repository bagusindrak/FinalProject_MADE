package com.thinking.submission5.fragment;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.thinking.submission5.Constant;
import com.thinking.submission5.MovieViewModel;
import com.thinking.submission5.R;
import com.thinking.submission5.adapter.CardViewAdapter;

import java.util.Objects;

import static com.thinking.submission5.Constant.SECTION_MOVIE;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

   private RecyclerView rvMovies;
   private ImageView img;
   private ProgressBar progressBar;
   private MovieViewModel movieViewModel;
   private CardViewAdapter cardViewHeroAdapter;
   private int index = 1;


   public static FavoriteFragment newInstance(int index) {
      // Required empty public constructor
      FavoriteFragment fragment = new FavoriteFragment();
      Bundle bundle = new Bundle();
      bundle.putInt(Constant.ARG_SECTION_NUMBER, index);
      fragment.setArguments(bundle);
      return fragment;
   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
      // Inflate the layout for this fragment
      return inflater.inflate(R.layout.fragment_favorite, container, false);

   }

   private void initComponent(View view) {
      rvMovies = view.findViewById(R.id.rv_movies);
      rvMovies.setHasFixedSize(true);
      progressBar = view.findViewById(R.id.progressBar);
      img = view.findViewById(R.id.img_film);
      movieViewModel = new ViewModelProvider(getViewModelStore(),
              new ViewModelProvider.NewInstanceFactory()).get(MovieViewModel.class);
      showRecyclerCardView();
      movieViewModel.setDao(Objects.requireNonNull(getActivity()).getApplicationContext());
   }

   @Override
   public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      initComponent(view);

      if (getArguments() != null) {
         index = getArguments().getInt(Constant.ARG_SECTION_NUMBER);
         Intent intent = new Intent();
         intent.putExtra(Constant.ARG_SECTION_NUMBER, index);
      }
      if (index == Constant.SECTION_MOVIE) {
         showLoading(true);
         img.setVisibility(View.VISIBLE);
         if (movieViewModel.getMovieFav() != null) {
            movieViewModel.getMovieFav().observe(getViewLifecycleOwner(), movies -> {
               if (movies.size() > 0) {
                  img.setVisibility(View.GONE);
                  showLoading(false);
               } else {
                  img.setVisibility(View.VISIBLE);
                  showLoading(false);
                  if (index == SECTION_MOVIE)
                     showSnackbarMessage("Data Movie Kosong");
               }
               cardViewHeroAdapter.setListMoviesFav(movies, Constant.SECTION_MOVIE);
            });
         }
      } else {
         showLoading(true);
         img.setVisibility(View.VISIBLE);
         if (movieViewModel.getTvFav() != null) {
            movieViewModel.getTvFav().observe(getViewLifecycleOwner(), movies -> {
               if (movies.size() > 0) {
                  img.setVisibility(View.GONE);
                  showLoading(false);
               } else {
                  img.setVisibility(View.VISIBLE);
                  showLoading(false);
                  showSnackbarMessage("Data Tv Kosong");
               }
               cardViewHeroAdapter.setListMoviesFav(movies, 2);
            });
         }
      }
   }

   private void showRecyclerCardView() {
      rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
      cardViewHeroAdapter = new CardViewAdapter(getActivity());
      cardViewHeroAdapter.notifyDataSetChanged();
      rvMovies.setAdapter(cardViewHeroAdapter);
   }

   private void showLoading(Boolean state) {
      if (state) {
         progressBar.setVisibility(View.VISIBLE);
      } else {
         progressBar.setVisibility(View.GONE);
      }
   }

   private void showSnackbarMessage(String message) {
      Snackbar.make(rvMovies, message, Snackbar.LENGTH_SHORT).show();
   }

   @Override
   public void onConfigurationChanged(@NonNull Configuration newConfig) {
      super.onConfigurationChanged(newConfig);
   }

   @Override
   public void onDetach() {
      super.onDetach();
   }

   @Override
   public void onDestroy() {
      super.onDestroy();
   }
}
