package com.erolaksoy.mynewsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.erolaksoy.mynewsapp.R
import com.erolaksoy.mynewsapp.adapters.NewsFeedAdapter
import com.erolaksoy.mynewsapp.adapters.OnClickListener
import com.erolaksoy.mynewsapp.adapters.OnLongClickListener
import com.erolaksoy.mynewsapp.databinding.FragmentCategorylistBinding
import com.erolaksoy.mynewsapp.ui.base.BaseFragment
import com.erolaksoy.mynewsapp.viewmodels.CategoriesViewModel
import kotlinx.android.synthetic.main.fragment_categorylist.*
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.fragment_news.newsRecyclerView

class CategoryListFragment : Fragment() {

    private lateinit var newsAdapter: NewsFeedAdapter
    private var newsCategory: String? = null
    val viewModel: CategoriesViewModel by lazy {
        ViewModelProvider(this).get(CategoriesViewModel::class.java)
    }

    companion object {
        fun getInstance(newsCategory: String): CategoryListFragment {
            val fragment = CategoryListFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.newsCategory = newsCategory
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding = FragmentCategorylistBinding.inflate(inflater, container, false)
        viewModel.getData(newsCategory!!)

        val adapter = NewsFeedAdapter(OnClickListener { }, OnLongClickListener { },
            OnClickListener { })

        binding.newsRecyclerViews.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        return binding.root

    }

}