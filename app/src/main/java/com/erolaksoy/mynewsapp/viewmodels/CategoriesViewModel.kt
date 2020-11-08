package com.erolaksoy.mynewsapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erolaksoy.mynewsapp.database.databaseModels.ArticleDb
import com.erolaksoy.mynewsapp.database.databaseModels.asArticleDb
import com.erolaksoy.mynewsapp.enums.FeedLoadingStatus
import com.erolaksoy.mynewsapp.models.Article
import com.erolaksoy.mynewsapp.network.NewsApiService
import com.erolaksoy.mynewsapp.network.NewsApiServiceBuilder
import kotlinx.coroutines.launch
import java.lang.Exception

class CategoriesViewModel : ViewModel() {

    private val _data = MutableLiveData<List<ArticleDb>>()
    val data: LiveData<List<ArticleDb>>
        get() = _data

    val loadingStatus = MutableLiveData<FeedLoadingStatus>()

    fun getData(categoryName: String) {
        viewModelScope.launch {
            loadingStatus.value = FeedLoadingStatus.LOADING
            try {
                val articleList =
                    NewsApiServiceBuilder.newsApiService.getCategoriesList(categoryName).articles.asArticleDb()
                _data.value = articleList
                loadingStatus.value = FeedLoadingStatus.LOADED
            } catch (e: Exception) {
                print(e.localizedMessage)
                loadingStatus.value = FeedLoadingStatus.FAILED
            }
        }
    }

}