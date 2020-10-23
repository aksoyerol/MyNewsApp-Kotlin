package com.erolaksoy.mynewsapp.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.erolaksoy.mynewsapp.R
import com.erolaksoy.mynewsapp.databinding.FragmentDetailBinding
import com.erolaksoy.mynewsapp.viewmodels.DetailViewModel
import com.erolaksoy.mynewsapp.viewmodels.DetailViewModelFactory

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        val application = requireActivity().application
        val arguments = DetailFragmentArgs.fromBundle(requireArguments())
        val viewModelFactory = DetailViewModelFactory(arguments.article)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}
