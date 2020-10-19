package com.erolaksoy.mynewsapp.models.deneme


import com.squareup.moshi.Json


data class Article(
    @Json(name = "source")
    val source: Source,
    @Json(name = "author")
    val author: Any?, // null
    @Json(name = "title")
    val title: String, // CNN reporter: I have never seen anything like this
    @Json(name = "description")
    val description: String, // The CDC on Monday abruptly reverted to its previous guidance about how coronavirus is transmitted, removing language about airborne transmission it had posted just days earlier. CNN's Elizabeth Cohen tells us more.
    @Json(name = "url")
    val url: String, // https://www.cnn.com/videos/health/2020/09/21/cdc-reverts-coronavirus-airborne-guidance-cohen-nr-vpx.cnn
    @Json(name = "urlToImage")
    val urlToImage: String, // https://cdn.cnn.com/cnnnext/dam/assets/200513041447-cdc-building-atlanta-super-tease.jpg
    @Json(name = "publishedAt")
    val publishedAt: String, // 2020-09-21T17:24:33Z
    @Json(name = "content")
    val content: String? // Chat with us in Facebook Messenger. Find out what's happening in the world as it unfolds.
)