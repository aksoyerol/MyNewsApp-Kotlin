package com.erolaksoy.mynewsapp.repository

import com.erolaksoy.mynewsapp.models.Article
import com.erolaksoy.mynewsapp.models.asArticleList
import com.erolaksoy.mynewsapp.network.NewsApiServiceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepository {

    suspend fun getDataFromApi() : List<Article> {
        return withContext(Dispatchers.IO) {
            NewsApiServiceBuilder.newsApiService.getAllNews().asArticleList()
        }
    }
}