package com.erolaksoy.mynewsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.erolaksoy.mynewsapp.database.databaseModels.ArticleDb
import com.erolaksoy.mynewsapp.databinding.FragmentBookmarkBinding
import com.erolaksoy.mynewsapp.databinding.FragmentBookmarkRowBinding


class BookmarkAdapter(private val clickListener: BookmarkOnClickListener) :
    ListAdapter<ArticleDb, BookmarkAdapter.ViewHolder>(BookmarkDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkAdapter.ViewHolder {
        return ViewHolder(FragmentBookmarkRowBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: BookmarkAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bookmarkImage.setOnClickListener { clickListener.onClick(item) }
        holder.bind(item)
    }


    class ViewHolder(private val binding: FragmentBookmarkRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val bookmarkImage= binding.imgBtnBookmark
        fun bind(article: ArticleDb) {
            binding.articleDb = article
            binding.executePendingBindings()
        }
    }
}

class BookmarkDiffUtil : DiffUtil.ItemCallback<ArticleDb>() {
    override fun areItemsTheSame(oldItem: ArticleDb, newItem: ArticleDb): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ArticleDb, newItem: ArticleDb): Boolean {
        return oldItem.url == newItem.url
    }
}

class BookmarkOnClickListener(private val clickListener: (article: ArticleDb) -> Unit) {
    fun onClick(article: ArticleDb) = clickListener(article)
}