package com.erolaksoy.mynewsapp.database.databaseModels

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erolaksoy.mynewsapp.models.Article
import com.erolaksoy.mynewsapp.models.Source
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "article")
@Parcelize
data class ArticleDb(
    @PrimaryKey
    val url: String,
    val author: String?,
    val title: String,
    val description: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?,
    var isBookmarked : Boolean = false
) : Parcelable {}

fun List<Article>.asArticleDb(): List<ArticleDb> {
    return map {
        ArticleDb(
            author = it.author,
            content = it.content,
            description = it.description,
            publishedAt = it.publishedAt,
            title = it.title,
            urlToImage = it.urlToImage,
            url = it.url
        )
    }
}

fun Source.asSourceDb(): SourceDb = SourceDb(id = id, name = name)

