package com.erolaksoy.mynewsapp.network

import com.erolaksoy.mynewsapp.models.ApiResponseModel

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://newsapi.org/v2/"
private const val API_KEY = "1eee93a0cbe942039dde9491e9649435"

interface NewsApiService {
    @GET("everything?q=cnn&pageSize=90&apiKey=$API_KEY")
    suspend fun getAllNews(): ApiResponseModel

    @GET("everything?")
    suspend fun getAllWithQuery(
        @Query("q") subjectKeyword: String,
        @Query("pageSize") pageSize: Int = 100,
        @Query("apiKey")apiKey: String = "1eee93a0cbe942039dde9491e9649435"
    ): ApiResponseModel
}

object NewsApiServiceBuilder {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val newsApiService : NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)
    }

}