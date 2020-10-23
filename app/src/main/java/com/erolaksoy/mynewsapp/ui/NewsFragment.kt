package com.erolaksoy.mynewsapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.erolaksoy.mynewsapp.R
import com.erolaksoy.mynewsapp.adapters.NewsFeedAdapter
import com.erolaksoy.mynewsapp.adapters.OnClickListener
import com.erolaksoy.mynewsapp.adapters.OnLongClickListener
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
        setHasOptionsMenu(true)
        val binding: FragmentNewsBinding = FragmentNewsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        val adapter = NewsFeedAdapter(OnClickListener {
            viewModel.navigateToDetailWithArticle.value = it
        }, OnLongClickListener {
            val callIntent: Intent = Uri.parse(it.url).let {
                Intent(Intent.ACTION_VIEW, it)
            }
            if (callIntent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(callIntent)
            }
        })
        binding.viewModel = viewModel
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
//            R.id.red ->{
//                Toast.makeText(requireContext(),"red click",Toast.LENGTH_LONG).show()
//                item.isChecked = !item.isChecked
//            }

        }
        return super.onOptionsItemSelected(item)
    }


}