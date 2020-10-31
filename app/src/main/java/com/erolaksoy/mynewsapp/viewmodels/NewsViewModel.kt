package com.erolaksoy.mynewsapp.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.erolaksoy.mynewsapp.database.NewsDatabase
import com.erolaksoy.mynewsapp.database.databaseModels.ArticleDb
import com.erolaksoy.mynewsapp.database.databaseModels.Bookmark
import com.erolaksoy.mynewsapp.enums.FeedLoadingStatus
import com.erolaksoy.mynewsapp.models.Article
import com.erolaksoy.mynewsapp.repository.NewsRepository
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.IllegalArgumentException

class NewsViewModel(app: Application) : AndroidViewModel(app) {
    val database = NewsDatabase.getInstance(app.applicationContext)
    private val repo = NewsRepository(database)
    val data = database.newsDao.getAll()


    private val _newsList = MutableLiveData<List<Article>>()
    val newsList: LiveData<List<Article>>
        get() = _newsList

    val navigateToDetailWithArticle = MutableLiveData<ArticleDb>()

    val loadingStatus = MutableLiveData<FeedLoadingStatus>()

    init {
        getDataFromRepos()
        //database.bookmarkDao.addBookmark(Bookmark(articleId = "https://lifehacker.com/the-out-of-touch-adults-guide-to-kid-culture-election-1845458690"))
//        val articles = database.newsDao.getBookMarkedEntities()
//        println("OUTOUT" + articles.size)
//        println(articles[0])


    }

    fun updateBookmarkEntity(articleDb: ArticleDb) {
        println("clicked ${articleDb.isBookmarked}")
        viewModelScope.launch {
            if (articleDb.isBookmarked) {
                articleDb.isBookmarked = false
                repo.updateBookmarkEntity(articleDb)
                getDataFromRepos()
            } else {
                articleDb.isBookmarked = true
                repo.updateBookmarkEntity(articleDb)
                getDataFromRepos()
            }

        }
    }

    private fun getDataFromRepo() {
        viewModelScope.launch {
            loadingStatus.value = FeedLoadingStatus.LOADING
            try {
                _newsList.value = repo.getDataFromApi()
                loadingStatus.value = FeedLoadingStatus.LOADED
            } catch (e: Exception) {
                println("HATA ${e.localizedMessage}")
                loadingStatus.value = FeedLoadingStatus.FAILED
            }
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