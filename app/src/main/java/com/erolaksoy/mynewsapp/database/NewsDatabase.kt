package com.erolaksoy.mynewsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.erolaksoy.mynewsapp.database.databaseModels.ArticleDb
import com.erolaksoy.mynewsapp.database.databaseModels.Bookmark

@Database(entities = [ArticleDb::class], version = 2, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {

    abstract val newsDao: NewsDao
    companion object {
        @Volatile
        private lateinit var INSTANCE: NewsDatabase

        fun getInstance(context: Context): NewsDatabase {
           synchronized(NewsDatabase::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE =
                        Room.databaseBuilder(context.applicationContext, NewsDatabase::class.java, "newsDatabase")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE
        }
    }
}