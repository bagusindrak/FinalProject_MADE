package com.thinking.submission5.database;


import android.content.Context;

import androidx.room.Room;

public class DbProvider {
   private static CatalogueDatabase instance;

   public static CatalogueDatabase getInstance(Context context)
   {
      if(DbProvider.instance == null)
      {
         DbProvider.instance = Room.databaseBuilder(
                 context, CatalogueDatabase.class, "dbmovie.db").allowMainThreadQueries().build();
      }

      return DbProvider.instance;
   }
}
