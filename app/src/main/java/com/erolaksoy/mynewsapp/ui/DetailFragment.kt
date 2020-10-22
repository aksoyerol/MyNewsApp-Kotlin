package com.erolaksoy.mynewsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.erolaksoy.mynewsapp.R
import com.erolaksoy.mynewsapp.databinding.FragmentDetailPageBinding

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailPageBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        val application = requireActivity().application


        return binding.root
    }

}