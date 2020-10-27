package com.erolaksoy.mynewsapp.models

import com.erolaksoy.mynewsapp.database.databaseModels.ArticleDb
import com.erolaksoy.mynewsapp.database.databaseModels.asSource

fun ApiResponseModel.asArticleList(): List<Article> {
    return articles.map {
        Article(
            url = it.url,
            urlToImage = it.urlToImage,
            source = it.source,
            title = it.title,
            publishedAt = it.publishedAt,
            description = it.description,
            content = it.content,
            author = it.author
        )
    }
}

fun List<ArticleDb>.asArticleList() : List<Article>{
    return map {
        Article(
            source = Source(id = "",name=""),
            title = it.title,
            url = it.url,
            urlToImage = it.urlToImage,
            publishedAt = it.publishedAt,
            description = it.description,
            content = it.content,
            author = it.author

        )
    }
}