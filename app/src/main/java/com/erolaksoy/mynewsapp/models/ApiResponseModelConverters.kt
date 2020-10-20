package com.erolaksoy.mynewsapp.models

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