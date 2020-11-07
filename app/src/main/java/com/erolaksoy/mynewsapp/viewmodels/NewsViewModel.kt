package com.erolaksoy.mynewsapp.viewmodels

import android.app.Application
import android.view.View
import androidx.lifecycle.*
import com.erolaksoy.mynewsapp.database.NewsDatabase
import com.erolaksoy.mynewsapp.database.databaseModels.ArticleDb
import com.erolaksoy.mynewsapp.database.databaseModels.Bookmark
import com.erolaksoy.mynewsapp.enums.BookmarkNotifyStatus
import com.erolaksoy.mynewsapp.enums.FeedLoadingStatus
import com.erolaksoy.mynewsapp.models.Article
import com.erolaksoy.mynewsapp.repository.NewsRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.IllegalArgumentException

class NewsViewModel(val app: Application) : AndroidViewModel(app) {
    val database = NewsDatabase.getInstance(app.applicationContext)
    private val repo = NewsRepository(database)
    var data = database.newsDao.getAll()

    private val _newsList = MutableLiveData<List<Article>>()
    val newsList: LiveData<List<Article>>
        get() = _newsList

    val navigateToDetailWithArticle = MutableLiveData<ArticleDb>()
    val loadingStatus = MutableLiveData<FeedLoadingStatus>()

    val showBookmarkSnackBar = MutableLiveData<BookmarkNotifyStatus>()

    init {
        getDataFromRepos()
    }

    fun updateBookmarkEntity(articleDb: ArticleDb) {
        println("clicked ${articleDb.isBookmarked}")
        viewModelScope.launch {
            if (articleDb.isBookmarked) {
                articleDb.isBookmarked = false
                repo.updateBookmarkEntity(articleDb)
                showBookmarkSnackBar.value = BookmarkNotifyStatus.REMOVED
            } else {
                articleDb.isBookmarked = true
                repo.updateBookmarkEntity(articleDb)
                showBookmarkSnackBar.value = BookmarkNotifyStatus.ADDED

            }
            refreshData()
        }
    }

    private fun refreshData() {
        viewModelScope.launch {
            repo.refreshDataRoom().value
        }
    }

    private fun getDataFromRepos() {
        viewModelScope.launch {
            loadingStatus.value = FeedLoadingStatus.LOADING
            try {
                repo.refreshDataFromDb()
                loadingStatus.value = FeedLoadingStatus.LOADED
            } catch (e: Exception) {
                loadingStatus.value = FeedLoadingStatus.FAILED
                println("HATA ${e.localizedMessage}")
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class NewsViewModelFactory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(app) as T
        }
        throw IllegalArgumentException("HATA ! NewsViewModelFactory")
    }

}