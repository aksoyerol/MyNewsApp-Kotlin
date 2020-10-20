package com.erolaksoy.mynewsapp.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.erolaksoy.mynewsapp.databinding.FragmentNewsRowBinding
import com.erolaksoy.mynewsapp.models.Article

class NewsFeedAdapter : ListAdapter<Article, NewsFeedAdapter.ViewHolder>(MyDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentNewsRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding(item)
    }

    class ViewHolder(private val binding: FragmentNewsRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binding(article: Article) {
            binding.article = article
            binding.executePendingBindings()
        }
    }
}

class MyDiffUtil : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.url == newItem.url
    }
}