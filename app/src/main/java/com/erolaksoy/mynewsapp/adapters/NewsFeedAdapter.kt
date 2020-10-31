package com.erolaksoy.mynewsapp.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.erolaksoy.mynewsapp.R
import com.erolaksoy.mynewsapp.database.databaseModels.ArticleDb
import com.erolaksoy.mynewsapp.databinding.FragmentNewsRowBinding
import com.erolaksoy.mynewsapp.models.Article

class NewsFeedAdapter(
    private val clickListener: OnClickListener,
    private val longClickListener: OnLongClickListener,
    private val bookmarkClickListener : OnClickListener
) :
    ListAdapter<ArticleDb, NewsFeedAdapter.ViewHolder>(MyDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentNewsRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if(item.isBookmarked){
            holder.bookmarkImage.setImageResource(R.drawable.bookmarked_black_image)
        }

        holder.itemView.setOnClickListener {
            clickListener.onClick(item)
        }

        holder.bookmarkImage.setOnClickListener {
            bookmarkClickListener.onBookmarkClick(item)
            if(item.isBookmarked){
                holder.bookmarkImage.setImageResource(R.drawable.bookmarked_black_image)
            }else{
                holder.bookmarkImage.setImageResource(R.drawable.bookmark_black_bordered)
            }

        }

        holder.itemView.setOnLongClickListener { view ->
            longClickListener.onLongClick(item)
            true
        }
        holder.binding(item)

    }

    class ViewHolder(private val binding: FragmentNewsRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val bookmarkImage = binding.bookmarkImage
        fun binding(article: ArticleDb) {
            binding.article = article
            binding.executePendingBindings()
        }
    }
}

class MyDiffUtil : DiffUtil.ItemCallback<ArticleDb>() {
    override fun areItemsTheSame(oldItem: ArticleDb, newItem: ArticleDb): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ArticleDb, newItem: ArticleDb): Boolean {
        return oldItem.url == newItem.url
    }
}

class OnClickListener(val clickListener: (article: ArticleDb) -> Unit) {
    fun onClick(article: ArticleDb) = clickListener(article)
    fun onBookmarkClick(article: ArticleDb) = clickListener(article)
}

class OnLongClickListener(val longClickListener: (article: ArticleDb) -> Unit) {
    fun onLongClick(article: ArticleDb) = longClickListener(article)
}
