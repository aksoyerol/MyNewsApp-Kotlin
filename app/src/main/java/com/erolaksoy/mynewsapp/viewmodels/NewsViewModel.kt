package com.erolaksoy.mynewsapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erolaksoy.mynewsapp.models.Article
import com.erolaksoy.mynewsapp.repository.NewsRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class NewsViewModel : ViewModel() {
    private val repo = NewsRepository()
    private val _newsList = MutableLiveData<List<Article>>()
    val newsList: LiveData<List<Article>>
        get() = _newsList


    init {
        getDataFromRepo()
    }

    private fun getDataFromRepo() {
        viewModelScope.launch {
            try {
                _newsList.value = repo.getDataFromApi()
            } catch (e: Exception) {
                println("HATA ${e.localizedMessage}")
            }
        }
    }
}