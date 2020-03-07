package com.thinking.submission5.adapter;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.thinking.submission5.Constant;
import com.thinking.submission5.R;
import com.thinking.submission5.activity.DetailActivity;
import com.thinking.submission5.entity.Movie;

import java.util.ArrayList;
import java.util.List;

import static com.thinking.submission5.Constant.EXTRA_MOVIE;
import static com.thinking.submission5.Constant.EXTRA_POSITION;
import static com.thinking.submission5.Constant.EXTRA_SECTION;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.CardViewViewHolder>{

   private ArrayList<Movie> listMovies = new ArrayList<>();
   private int section;
   private Activity activity;



   public CardViewAdapter(Activity activity) {
      this.activity = activity;
   }

   public void setListMovies(ArrayList<Movie> items, int section) {
      if (listMovies.size() > 0) {
         this.listMovies.clear();
      }
      listMovies.addAll(items);
      notifyDataSetChanged();
      this.section = section;
   }
   public void setListMoviesFav(List<Movie> items, int section) {
      if (listMovies.size() > 0) {
         this.listMovies.clear();
      }
      listMovies.addAll(items);
      notifyDataSetChanged();
      this.section = section;
   }

   @Override
   public int getItemViewType(int position) {
      return super.getItemViewType(position);
   }

   @NonNull
   @Override
   public CardViewAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
      View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_movie, parent, false);
      return new CardViewViewHolder(itemView);

   }

   @Override
   public void onBindViewHolder(@NonNull final CardViewAdapter.CardViewViewHolder holder, final int position) {
      final Movie movies = listMovies.get(position);
      holder.progressBar.setVisibility(View.VISIBLE);
      Glide.with(holder.itemView.getContext())
              .asBitmap()
              .load(Constant.BASE_URL_IMAGE + movies.getPosterPath())
              .listener(new RequestListener<Bitmap>() {
                 @Override
                 public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                    holder.progressBar.setVisibility(View.VISIBLE);
                    return false;
                 }

                 @Override
                 public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                    holder.progressBar.setVisibility(View.GONE);
                    return false;
                 }
              })
              .apply(new RequestOptions().override(350, 550))
              .into(holder.imgPhoto);
      holder.tvName.setText(movies.getTitle());
      holder.tvDescription.setText(movies.getOverview());

      holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
         @Override
         public boolean onLongClick(View v) {
            Toast.makeText(v.getContext(), String.valueOf(holder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
            return false;
         }
      });
      holder.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Intent moveWithObjectIntent = new Intent(activity, DetailActivity.class);
            moveWithObjectIntent.putExtra(EXTRA_SECTION, section);
            moveWithObjectIntent.putExtra(EXTRA_MOVIE, listMovies.get(holder.getAdapterPosition()));
            moveWithObjectIntent.putExtra(EXTRA_POSITION, holder.getAdapterPosition());
            activity.startActivity(moveWithObjectIntent);
         }
      });

   }

   @Override
   public int getItemCount() {
      return listMovies.size();
   }


   public class CardViewViewHolder extends RecyclerView.ViewHolder {

      TextView tvName, tvDescription;
      ImageView imgPhoto;
      ProgressBar progressBar;

      public CardViewViewHolder(@NonNull View itemView) {
         super(itemView);
         imgPhoto = itemView.findViewById(R.id.img_item_photo);
         tvName = itemView.findViewById(R.id.tv_item_name);
         tvDescription = itemView.findViewById(R.id.tv_item_description);
         progressBar = itemView.findViewById(R.id.progressBarImg);
      }
   }


}
