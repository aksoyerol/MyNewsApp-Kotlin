package com.erolaksoy.mynewsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.erolaksoy.mynewsapp.adapters.NewsFeedAdapter
import com.erolaksoy.mynewsapp.adapters.OnClickListener
import com.erolaksoy.mynewsapp.databinding.FragmentNewsBinding
import com.erolaksoy.mynewsapp.viewmodels.NewsViewModel

class NewsFragment : Fragment() {

    private val viewModel: NewsViewModel by lazy {
        ViewModelProvider(this).get(NewsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentNewsBinding = FragmentNewsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        val adapter = NewsFeedAdapter(OnClickListener {
            viewModel.navigateToDetailWithArticle.value = it
        })
        binding.newsRecyclerView.adapter = adapter

        viewModel.newsList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.navigateToDetailWithArticle.observe(viewLifecycleOwner, Observer {
            it?.let {
                val action = NewsFragmentDirections.actionNewsPageToDetailFragment(it)
                findNavController().navigate(action)
                viewModel.navigateToDetailWithArticle.value = null
            }
        })
        return binding.root
    }


}