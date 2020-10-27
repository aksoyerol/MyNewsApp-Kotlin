package com.erolaksoy.mynewsapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.erolaksoy.mynewsapp.database.NewsDatabase
import com.erolaksoy.mynewsapp.database.databaseModels.ArticleDb
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.IllegalArgumentException

class BookmarkViewModel(application: Application) : AndroidViewModel(application) {
    private val _bookmarkList = MutableLiveData<List<ArticleDb>>()
    val bookmarkList: LiveData<List<ArticleDb>>
        get() = _bookmarkList

    val database = NewsDatabase.getInstance(application.applicationContext)

    init {
        getDataFromDb()
        println(bookmarkList.value)

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

}

class BookmarkViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookmarkViewModel::class.java)) {
            return BookmarkViewModel(application) as T
        }
        throw IllegalArgumentException("HATA BookmarkViewModelFactory")
    }

}