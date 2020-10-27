package com.erolaksoy.mynewsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import com.erolaksoy.mynewsapp.R
import com.erolaksoy.mynewsapp.database.NewsDatabase
import com.erolaksoy.mynewsapp.database.databaseModels.ArticleDb
import com.erolaksoy.mynewsapp.databinding.FragmentSecondBinding
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentSecondBinding.inflate(inflater, container, false)
        val app = requireActivity().application


        val listDb =
            NewsDatabase.getInstance(app.applicationContext).newsDao.getAllBookmarkedEntity()

        println(listDb)
        val listItem = arrayOfNulls<String>(listDb.size)

        listItem[0] = "erol erol erol"
        for (i in 1 until listDb.size) {
            listItem[i] = listDb[i].url
        }

        val adapter =
            ArrayAdapter(app.applicationContext, android.R.layout.simple_list_item_1, listItem)
        listView.adapter = adapter

        return binding.root
    }

}