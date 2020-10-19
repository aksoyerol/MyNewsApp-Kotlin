package com.erolaksoy.mynewsapp.models.deneme


import com.squareup.moshi.Json

data class Source(
    @Json(name = "id")
    val id: String?, // cnn
    @Json(name = "name")
    val name: String // CNN
)