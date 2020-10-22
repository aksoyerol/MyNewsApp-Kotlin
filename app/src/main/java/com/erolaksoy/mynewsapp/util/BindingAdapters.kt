package com.erolaksoy.mynewsapp.util

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.erolaksoy.mynewsapp.R
import java.time.Instant
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@BindingAdapter("bind_image")
fun bindImage(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
    val imgUri = imageUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(imgUri)
            .apply(RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_loading_image_animation)
                .error(R.drawable.ic_loading_image)
            )
            .into(imageView)
    }
}

@BindingAdapter("bind_article_title")
fun bindArticleTitle(
    textView:
    TextView, title: String
) {
    if (title.length > 65) {
        val txt = title.substring(0, 65) + " ..."
        textView.text = txt
    } else {
        textView.text = title
    }
}

@BindingAdapter("bind_date")
fun bindFormatDate(txtView: TextView, date: String) {
    val timeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME
    val accessor: OffsetDateTime = OffsetDateTime.parse(date,timeFormatter)
    val myDate = Date.from(Instant.from(accessor))
    txtView.text = accessor.toLocalDate().toString()

}