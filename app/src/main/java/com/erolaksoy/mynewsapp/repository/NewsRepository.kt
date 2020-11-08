package com.erolaksoy.mynewsapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.erolaksoy.mynewsapp.database.NewsDatabase
import com.erolaksoy.mynewsapp.database.databaseModels.ArticleDb
import com.erolaksoy.mynewsapp.database.databaseModels.asArticleDb
import com.erolaksoy.mynewsapp.models.Article
import com.erolaksoy.mynewsapp.models.asArticleList
import com.erolaksoy.mynewsapp.network.NewsApiServiceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepository(private val database: NewsDatabase) {

    //val data: LiveData<List<ArticleDb>> = database.newsDao.getAll()

    suspend fun getDataFromApi(): List<Article> {
        return withContext(Dispatchers.IO) {
            NewsApiServiceBuilder.newsApiService.getAllNews().asArticleList()
        }
    }

    suspend fun refreshDataFromDb() {
        withContext(Dispatchers.IO) {
            val networkData = NewsApiServiceBuilder.newsApiService.getAllNews().asArticleList()
            database.newsDao.insertAll(networkData.asArticleDb())
        }
    }

    suspend fun refreshDataRoom(): LiveData<List<ArticleDb>> {
        return withContext(Dispatchers.IO) {
            database.newsDao.getAll()
        }
    }

    suspend fun updateBookmarkEntity(articleDb: ArticleDb) {
        return withContext(Dispatchers.Default) {
            database.newsDao.updateEntity(articleDb)
        }
    }

    suspend fun getCategoryList(categoryName : String){
        return withContext(Dispatchers.IO){
            NewsApiServiceBuilder.newsApiService.getCategoriesList(categoryName).articles.asArticleDb()
        }
    }


}