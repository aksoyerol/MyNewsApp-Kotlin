package com.erolaksoy.mynewsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.erolaksoy.mynewsapp.databinding.FragmentNewsBinding
import com.erolaksoy.mynewsapp.network.NewsApiServiceBuilder
import com.erolaksoy.mynewsapp.viewmodels.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.coroutines.launch

class NewsFragment : Fragment() {

    private val viewModel: NewsViewModel by lazy {
        ViewModelProvider(this).get(NewsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentNewsBinding = FragmentNewsBinding.inflate(inflater, container, false)

        viewLifecycleOwner.lifecycleScope.launch {
            val data = NewsApiServiceBuilder.newsApiService.getAllWithQuery("apple",20)
            textView.text = data.articles.get(1).content.toString()

        }
        return binding.root
    }
}