package com.erolaksoy.mynewsapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erolaksoy.mynewsapp.database.databaseModels.ArticleDb
import com.erolaksoy.mynewsapp.database.databaseModels.asArticleDb
import com.erolaksoy.mynewsapp.models.Article
import com.erolaksoy.mynewsapp.network.NewsApiService
import com.erolaksoy.mynewsapp.network.NewsApiServiceBuilder
import kotlinx.coroutines.launch
import java.lang.Exception

class CategoriesViewModel : ViewModel() {

    private val _data = MutableLiveData<List<ArticleDb>>()
    val data: LiveData<List<ArticleDb>>
        get() = _data
    private var api: NewsApiService? = null

    init {
        api = NewsApiServiceBuilder.newsApiService
    }


    fun getData(categoryName: String) {
        viewModelScope.launch {
            try {

                    val network =
                        NewsApiServiceBuilder.newsApiService.getCategoriesList(categoryName).articles.asArticleDb()
                    _data.value = network
                    println(network)


            } catch (e: Exception) {
                print(e.localizedMessage)
            }
        }

    }

}