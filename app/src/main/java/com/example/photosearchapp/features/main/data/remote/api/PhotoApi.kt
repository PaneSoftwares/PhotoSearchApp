package com.example.photosearchapp.features.main.data.remote.api

import com.example.photosearchapp.BuildConfig
import com.example.photosearchapp.features.main.data.remote.dto.PhotoListDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PhotoApi {

    @GET("search")
    suspend fun getSearchPhotoList(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Header("Authorization") apiKey: String
    ): PhotoListDto

    companion object {
        const val BASE_URL = "https://api.pexels.com/v1/"
        const val API_KEY = BuildConfig.API_KEY
    }
}