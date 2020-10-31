package com.erolaksoy.mynewsapp.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.erolaksoy.mynewsapp.database.NewsDatabase
import com.erolaksoy.mynewsapp.database.databaseModels.ArticleDb
import com.erolaksoy.mynewsapp.models.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(val article: ArticleDb, val app: Application) : AndroidViewModel(app) {

    val incomingArticle = MutableLiveData<ArticleDb>()
    val database = NewsDatabase.getInstance(app.applicationContext)

    init {
        incomingArticle.value = article
    }


    fun saveToBookmark() {
        if (!article.isBookmarked) {
            viewModelScope.launch {
                article.isBookmarked = true
                addToBookmark(article)
            }
        } else {
            viewModelScope.launch {
                article.isBookmarked = false
                addToBookmark(article)
            }
        }
    }

    suspend fun addToBookmark(article: ArticleDb) {
        withContext(Dispatchers.IO) {
            database.newsDao.updateEntity(article)
            println("calisti")
        }
    }

}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(private val article: ArticleDb, private val appContext: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(article, appContext) as T
        }
        throw IllegalArgumentException("HATAAAAAA DetailViewModelFactory")
    }
}

