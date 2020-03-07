package com.thinking.submission5.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.snackbar.Snackbar;
import com.thinking.submission5.Constant;
import com.thinking.submission5.MovieViewModel;
import com.thinking.submission5.R;
import com.thinking.submission5.entity.Movie;
import com.thinking.submission5.entity.Tv;

import java.util.Objects;

import static com.thinking.submission5.Constant.BASE_URL_IMAGE;
import static com.thinking.submission5.Constant.EXTRA_MOVIE;
import static com.thinking.submission5.Constant.EXTRA_POSITION;
import static com.thinking.submission5.Constant.EXTRA_SECTION;
import static com.thinking.submission5.Constant.SECTION_MOVIE;


public class DetailActivity extends AppCompatActivity {

   private TextView tvName, tvDescription;
   private ImageView img;
   private ProgressBar progressBar;
   private boolean isFav = false;
   private int index = 1;
   private MovieViewModel movieViewModel;
   private Intent i = new Intent();

   private Movie movie;
   private Tv tv;

   private void initComponent() {
      tvName = findViewById(R.id.txt_name);
      tvDescription = findViewById(R.id.txt_description);
      img = findViewById(R.id.img_photo);
      progressBar = findViewById(R.id.progressBar);
      movieViewModel = new ViewModelProvider(getViewModelStore(),
              new ViewModelProvider.NewInstanceFactory()).get(MovieViewModel.class);
   }

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_detail);
      initComponent();

      index = getIntent().getIntExtra(EXTRA_SECTION, 0);
      movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
      movieViewModel.setItemMovie(movie);
      movieViewModel.setDao(getApplicationContext());
      showLoading(true);
      if (movie != null) {
         if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(movie.getTitle());
         }
         Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
         getSupportActionBar().setDisplayShowHomeEnabled(true);
         if (index == SECTION_MOVIE) {

         } else {
         }
         tvName.setText(movie.getTitle());
         tvDescription.setText(movie.getOverview());
         Glide.with(this)
                 .asBitmap()
                 .load(BASE_URL_IMAGE+movie.getPosterPath())
                 .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                       showLoading(true);
                       return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                       showLoading(false);
                       return false;
                    }
                 })
                 .into(img);
      }

   }


   private void showLoading(Boolean state) {
      if (state) {
         progressBar.setVisibility(View.VISIBLE);
      } else {
         progressBar.setVisibility(View.GONE);
      }
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.detail_menu, menu);
      return super.onCreateOptionsMenu(menu);
   }

   @Override
   public boolean onPrepareOptionsMenu(Menu menu) {
      MenuItem item = menu.findItem(R.id.action_favorite);
      if (movie != null) {
         if (index == SECTION_MOVIE) {
            if (movieViewModel.isFavMovie()) {
               setIconFav(item, true);
            } else {
               setIconFav(item, false);
            }
         } else {
            if (movieViewModel.isFavTv()) {
               setIconFav(item, true);
            } else {
               setIconFav(item, false);
            }
         }
      }
      item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
         @Override
         public boolean onMenuItemClick(MenuItem item) {
            if (!isFav) {
               if (index == SECTION_MOVIE) {
                  movieViewModel.insertMovie();
                  setIconFav(item, true);
                  showSnackbarMessage("Sukses nemambah Movie");
                  i.getIntExtra(EXTRA_SECTION, index);

               } else {
                  movieViewModel.insertTv();
                  setIconFav(item, true);
                  showSnackbarMessage("Sukses nemambah TV Show");
                  i.putExtra(EXTRA_SECTION, index);
               }
               i.putExtra(EXTRA_POSITION, getIntent().getIntExtra(EXTRA_POSITION, 0));
               i.putExtra(EXTRA_MOVIE, tv);
               setResult(Constant.RESULT_ADD, i);
            } else {
               if (index == SECTION_MOVIE) {
                  movieViewModel.deleteMovieFav();
               } else {
                  movieViewModel.deleteTvFav();
               }
               i.putExtra(EXTRA_POSITION, getIntent().getIntExtra(EXTRA_POSITION, 0));
               i.putExtra(EXTRA_MOVIE, tv);
               setResult(Constant.RESULT_DELETE, i);
               setIconFav(item, false);
               showSnackbarMessage("Sukses menghapus data");
            }

            return true;
         }
      });

      return super.onPrepareOptionsMenu(menu);
   }

   @Override
   public boolean onOptionsItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()) {
         case android.R.id.home:
            finish();
            break;
         case R.id.action_favorite:
            invalidateOptionsMenu();
            break;
      }
      return super.onOptionsItemSelected(item);
   }

   @Override
   protected void onDestroy() {
      super.onDestroy();
   }

   private void showSnackbarMessage(String message) {
      Snackbar.make(findViewById(R.id.scroll), message, Snackbar.LENGTH_SHORT).show();
   }

   private void setIconFav(MenuItem item, boolean state) {
      if (!state) {
         isFav = false;
         item.setIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite_border));
      } else {
         isFav = true;
         item.setIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite));
      }

   }

}
