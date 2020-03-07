package com.thinking.submission5.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.thinking.submission5.R;
import com.thinking.submission5.adapter.SectionsPagerAdapter;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {


   private boolean showMenu = true;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
      ViewPager viewPager = findViewById(R.id.view_pager);
      viewPager.setAdapter(sectionsPagerAdapter);
      TabLayout tabs = findViewById(R.id.tabs);
      tabs.setupWithViewPager(viewPager);
      showMenu = true;

      Objects.requireNonNull(getSupportActionBar()).setElevation(0);
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.main_menu, menu);
      MenuItem menuFav = menu.findItem(R.id.action_favorite);
      MenuItem menuSet = menu.findItem(R.id.action_change_settings);
      MenuItem menuSearch = menu.findItem(R.id.action_search);
      SearchView searchView = (SearchView) menuSearch.getActionView();
      searchView.setQueryHint(getString(R.string.search));

      menuSearch.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
         @Override
         public boolean onMenuItemActionExpand(MenuItem item) {
            showMenu =false;
            return true;
         }

         @Override
         public boolean onMenuItemActionCollapse(MenuItem item) {
            showMenu = true;
            return true;
         }
      });


      searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
         @Override
         public boolean onQueryTextSubmit(String query) {
            return false;
         }

         @Override
         public boolean onQueryTextChange(String newText) {
            return false;
         }
      });
      return super.onCreateOptionsMenu(menu);
   }

   @Override
   public boolean onPrepareOptionsMenu(Menu menu) {
      MenuItem menuFav = menu.findItem(R.id.action_favorite);
      MenuItem menuSet = menu.findItem(R.id.action_change_settings);
      MenuItem menuSearch = menu.findItem(R.id.action_search);
      SearchView searchView = (SearchView) menuSearch.getActionView();
      if(searchView.isFocusable()){
         menuFav.setVisible(false);
         menuSet.setVisible(false);
      }else {
         menuFav.setVisible(true);
         menuSet.setVisible(true);
      }
      return super.onPrepareOptionsMenu(menu);
   }

   @Override
   public boolean onOptionsItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()) {
         case R.id.action_change_settings:
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
            return true;
         case R.id.action_favorite:
            Intent i = new Intent(this, FavoriteActivity.class);
            startActivity(i);
            break;
      }
//      invalidateOptionsMenu();
      return super.onOptionsItemSelected(item);
   }
}
