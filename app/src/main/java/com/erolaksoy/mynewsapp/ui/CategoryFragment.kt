package com.erolaksoy.mynewsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.erolaksoy.mynewsapp.R
import com.erolaksoy.mynewsapp.adapters.ViewPagerAdapter
import com.erolaksoy.mynewsapp.databinding.FragmentCategoryBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.*

class CategoryFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
       // (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        val binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val viewpager = binding.viewPager
        val adapter =
            ViewPagerAdapter(requireActivity().supportFragmentManager, viewLifecycleOwner.lifecycle)

        adapter.addFragment(BookmarkFragment(), "Bookmarks")
        adapter.addFragment(NewsFragment(), "News")
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            viewpager.setCurrentItem(tab.position, true)
            tab.text = adapter.titleList[position]

        }.attach()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //(requireActivity() as AppCompatActivity).supportActionBar?.show()
    }

}
