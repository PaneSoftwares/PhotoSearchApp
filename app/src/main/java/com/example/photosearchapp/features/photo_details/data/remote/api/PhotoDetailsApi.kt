package com.example.photosearchapp.features.photo_details.data.remote.api

import com.example.photosearchapp.features.main.data.remote.dto.PhotoDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoDetailsApi {

    @GET("photos")
    suspend fun getDetails(
        @Query("id") id: Int,
        @Query("api_key") apiKey: String
    ): PhotoDto

}