package com.thinking.submission5.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.thinking.submission5.R;
import com.thinking.submission5.adapter.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
      ViewPager viewPager = findViewById(R.id.view_pager);
      viewPager.setAdapter(sectionsPagerAdapter);
      TabLayout tabs = findViewById(R.id.tabs);
      tabs.setupWithViewPager(viewPager);

      getSupportActionBar().setElevation(0);
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.main_menu, menu);
      return super.onCreateOptionsMenu(menu);
   }



   @Override
   public boolean onOptionsItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()) {
         case R.id.action_change_settings:
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
            return true;
         case R.id.action_favorite:
            Intent i = new Intent(this,FavoriteActivity.class);
            startActivity(i);
            break;
      }
      return super.onOptionsItemSelected(item);
   }
}
