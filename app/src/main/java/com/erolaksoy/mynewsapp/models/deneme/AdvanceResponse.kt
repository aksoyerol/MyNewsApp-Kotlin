package com.erolaksoy.mynewsapp.models.deneme


import com.squareup.moshi.Json


data class AdvanceResponse(
    @Json(name = "status")
    val status: String, // ok
    @Json(name = "totalResults")
    val totalResults: Int, // 26155
    @Json(name = "articles")
    val articles: List<Article>
)