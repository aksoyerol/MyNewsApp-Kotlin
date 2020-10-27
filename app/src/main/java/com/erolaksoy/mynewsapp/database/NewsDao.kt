package com.erolaksoy.mynewsapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erolaksoy.mynewsapp.database.databaseModels.ArticleDb
import com.erolaksoy.mynewsapp.database.databaseModels.ArticlesAndBookmarks


@Dao
interface NewsDao {

    @Query("SELECT * FROM article")
    fun getAll(): LiveData<List<ArticleDb>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(articleList: List<ArticleDb>)

    @Update
    suspend fun updateEntity(updatedArticle : ArticleDb)

    @Query("SELECT * FROM article WHERE isBookmarked=1")
    fun getAllBookmarkedEntity() : List<ArticleDb>


//    @Query("DELETE FROM sourceDb")
//    fun deleteAll()

//    @Transaction
//    @Query("SELECT * FROM article")
//    fun getBookMarkedEntities() : List<ArticlesAndBookmarks>


}