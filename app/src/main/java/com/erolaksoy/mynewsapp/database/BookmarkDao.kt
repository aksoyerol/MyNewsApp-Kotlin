package com.erolaksoy.mynewsapp.database

import androidx.room.*
import com.erolaksoy.mynewsapp.database.databaseModels.ArticlesAndBookmarks
import com.erolaksoy.mynewsapp.database.databaseModels.Bookmark


interface BookmarkDao {

    @Insert()
    fun addBookmark(bookmark: Bookmark)
//
//    @Transaction
//    @Query("SELECT * FROM article")
//    fun getAll() : List<ArticlesAndBookmarks>


}