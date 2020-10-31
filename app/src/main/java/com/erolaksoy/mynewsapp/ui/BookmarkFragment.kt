package com.erolaksoy.mynewsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.erolaksoy.mynewsapp.adapters.BookmarkAdapter
import com.erolaksoy.mynewsapp.adapters.BookmarkOnClickListener
import com.erolaksoy.mynewsapp.databinding.FragmentBookmarkBinding
import com.erolaksoy.mynewsapp.viewmodels.BookmarkViewModel
import com.erolaksoy.mynewsapp.viewmodels.BookmarkViewModelFactory

class BookmarkFragment : Fragment() {
    private val viewModelFactory: BookmarkViewModelFactory by lazy {
        BookmarkViewModelFactory(requireActivity().application)
    }
    private val viewModel: BookmarkViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(BookmarkViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        val adapter = BookmarkAdapter(BookmarkOnClickListener {

        })

        binding.bookmarkRecyclerView.adapter = adapter
        viewModel.bookmarkList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }

}