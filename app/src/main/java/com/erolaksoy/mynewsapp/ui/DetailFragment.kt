package com.erolaksoy.mynewsapp.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.erolaksoy.mynewsapp.R
import com.erolaksoy.mynewsapp.databinding.FragmentDetailBinding
import com.erolaksoy.mynewsapp.viewmodels.DetailViewModel
import com.erolaksoy.mynewsapp.viewmodels.DetailViewModelFactory

class DetailFragment : Fragment() {
    private val arguments : DetailFragmentArgs by lazy {
        DetailFragmentArgs.fromBundle(requireArguments())
    }
    private val viewModelFactory : DetailViewModelFactory by lazy {
        DetailViewModelFactory(arguments.articledb, requireActivity().application)
    }
    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        val application = requireActivity().application

        binding.viewModel = viewModel
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.savePost){
                viewModel.saveToBookmark()
            }
        return true
    }

}
