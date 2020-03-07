package com.thinking.submission5.fragment;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thinking.submission5.MovieViewModel;
import com.thinking.submission5.R;
import com.thinking.submission5.adapter.CardViewAdapter;

import java.util.Locale;

import static com.thinking.submission5.Constant.ARG_SECTION_NUMBER;
import static com.thinking.submission5.Constant.SECTION_MOVIE;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

   private RecyclerView rvMovies;
   private ProgressBar progressBar;
   private CardViewAdapter cardViewHeroAdapter;
   private MovieViewModel movieViewModel;
   private static String sDefSystemLanguage;
   private int index = 1;


   public static MovieFragment newInstance(int index) {
      // Required empty public constructor
      MovieFragment fragment = new MovieFragment();
      Bundle bundle = new Bundle();
      bundle.putInt(ARG_SECTION_NUMBER, index);
      fragment.setArguments(bundle);
      sDefSystemLanguage = Locale.getDefault().getLanguage();
      return fragment;
   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
      // Inflate the layout for this fragment
      return inflater.inflate(R.layout.fragment_movie, container, false);
   }


   @Override
   public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);

      rvMovies = view.findViewById(R.id.rv_movies);
      rvMovies.setHasFixedSize(true);
      progressBar = view.findViewById(R.id.progressBar);
      movieViewModel = new ViewModelProvider(getViewModelStore(),
              new ViewModelProvider.NewInstanceFactory()).get(MovieViewModel.class);
      showRecyclerCardView();

      if (getArguments() != null) {
         index = getArguments().getInt(ARG_SECTION_NUMBER);
         Intent intent = new Intent();
         intent.putExtra(ARG_SECTION_NUMBER, index);
      }
      if (index == SECTION_MOVIE) {
         movieViewModel.setMovieAPI();
         showLoading(true);
         movieViewModel.getMovies().observe(getViewLifecycleOwner(), movies -> {
            if (movies != null) {
               cardViewHeroAdapter.setListMovies(movies, SECTION_MOVIE);
               showLoading(false);
            }
         });
      } else {
         movieViewModel.setTvShowAPI();
         showLoading(true);
         movieViewModel.getMovies().observe(getViewLifecycleOwner(), movies -> {
            if (movies != null) {
               cardViewHeroAdapter.setListMovies(movies, 2);
               showLoading(false);
            }
         });
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

   @Override
   public void onConfigurationChanged(@NonNull Configuration newConfig) {
      super.onConfigurationChanged(newConfig);
      sDefSystemLanguage = newConfig.locale.getLanguage();
   }

   @Override
   public void onDestroy() {
      super.onDestroy();
   }
}
