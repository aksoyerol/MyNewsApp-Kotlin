package com.erolaksoy.mynewsapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.erolaksoy.mynewsapp.models.Article
import java.lang.IllegalArgumentException

class DetailViewModel(article : Article) : ViewModel() {

}

class DetailViewModelFactory(private val article : Article) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(article) as T
        }
        throw IllegalArgumentException("HATAAAAAA DetailViewModelFactory")
    }

}