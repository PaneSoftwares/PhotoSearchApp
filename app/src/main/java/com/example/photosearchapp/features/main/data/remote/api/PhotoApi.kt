package com.example.photosearchapp.features.main.data.remote.api

import com.example.photosearchapp.features.main.data.remote.dto.PhotoDto
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

    @GET("photos")
    suspend fun getDetails(
        @Query("id") id: Int,
        @Query("api_key") apiKey: String
    ): PhotoDto

    companion object {
        const val BASE_URL = "https://api.pexels.com/v1/"
        const val API_KEY = "u2e2G8g0RY2VPEVekeA6fwMxMvXUHU3nSP7ijAh7bLaCeR4Vi18RqsCI"
//        const val API_KEY = BuildConfig.API_KEY
    }
}