package com.erolaksoy.mynewsapp.util

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("bind_image")
fun bindImage(imageView: ImageView, imageUrl: String) {
    val imgUri = imageUrl.toUri().buildUpon().scheme("https").build()
    imageUrl.let {
        Glide.with(imageView.context).load(imgUri).apply(RequestOptions().centerCrop())
            .into(imageView)
    }
}

@BindingAdapter("bind_article_title")
fun bindArticleTitle(textView: TextView, title: String) {
    if (title.length > 65) {
        val txt = title.substring(0, 65) + " ..."
        textView.text = txt
    } else {
        textView.text = title
    }

}
