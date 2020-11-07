package com.erolaksoy.mynewsapp.ui.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.viewpager2.adapter.FragmentStateAdapter

class BaseFragmentAdapter(
    private val fm: FragmentManager,
    private val lifecycle: Lifecycle,
    private val fragmentList: ArrayList<Fragment>,
    private val titleList: ArrayList<String>,
) :
    FragmentStateAdapter(fm, lifecycle) {

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        titleList.add(title)
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}