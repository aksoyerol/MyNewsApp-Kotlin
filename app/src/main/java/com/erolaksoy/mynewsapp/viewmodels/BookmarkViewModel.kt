package com.erolaksoy.mynewsapp.viewmodels

import android.app.Application
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.*
import com.erolaksoy.mynewsapp.database.NewsDatabase
import com.erolaksoy.mynewsapp.database.databaseModels.ArticleDb
import com.erolaksoy.mynewsapp.repository.NewsRepository
import kotlinx.coroutines.launch

class BookmarkViewModel(application: Application) : AndroidViewModel(application) {
    private val _bookmarkList = MutableLiveData<List<ArticleDb>>()
    val bookmarkList: LiveData<List<ArticleDb>>
        get() = _bookmarkList

    val database = NewsDatabase.getInstance(application.applicationContext)
    val repo = NewsRepository(database)
    val showBookmarkList: LiveData<Boolean> = Transformations.map(_bookmarkList) { it ->
        it.isNotEmpty()
    }


    init {
        getDataFromDb()
    }

    private fun getDataFromDb() {
        viewModelScope.launch {
            try {
                _bookmarkList.value = database.newsDao.getAllBookmarkedEntity()
            } catch (e: Exception) {
                Log.e("HATA", e.localizedMessage)
            }
        }
    }

    private fun updateBookmarkedArticle(articleDb: ArticleDb) {
        viewModelScope.launch {
            repo.updateBookmarkEntity(articleDb)
            getDataFromDb()
        }
    }

    fun onClickTheImgButton(articleDb: ArticleDb) {
        println("clicked ${articleDb.isBookmarked}")
        if (articleDb.isBookmarked) {
            articleDb.isBookmarked = false
            updateBookmarkedArticle(articleDb)
        }
    }

}

class BookmarkViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookmarkViewModel::class.java)) {
            return BookmarkViewModel(application) as T
        }
        throw IllegalArgumentException("HATA BookmarkViewModelFactory")
    }

}