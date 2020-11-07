package com.erolaksoy.mynewsapp.ui

import androidx.fragment.app.Fragment
import com.erolaksoy.mynewsapp.R
import com.erolaksoy.mynewsapp.ui.base.BaseFragment
import com.erolaksoy.mynewsapp.ui.base.BaseFragmentAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_category.*

class CategoriesFragment : BaseFragment() {

    private var newsCategoryList = mutableListOf<String>()
    private val tabList = ArrayList<String>()
    private val fragments = ArrayList<Fragment>()

    override fun getLayoutId(): Int {
        return R.layout.fragment_category
    }

    override fun initView() {
        newsCategoryList.add("science")
        newsCategoryList.add("technology")
        newsCategoryList.add("general")
        newsCategoryList.add("business")
        newsCategoryList.add("health")
        for (item in newsCategoryList) {
            tabList.add(item)
            fragments.add(CategoryListFragment.getInstance(item))
        }

    }

    override fun lazyLoad() {
        viewPager.adapter = BaseFragmentAdapter(childFragmentManager, lifecycle,fragments,tabList)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            viewPager.setCurrentItem(tab.position, true)
            tab.text = tabList[position]
        }.attach()
        //viewPager.setCurrentItem(1,true)
        //viewPager.currentItem = 2
    }
}