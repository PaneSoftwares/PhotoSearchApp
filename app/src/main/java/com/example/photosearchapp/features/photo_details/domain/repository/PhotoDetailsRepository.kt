package com.example.photosearchapp.features.photo_details.domain.repository

import com.example.photosearchapp.features.main.domain.model.Photo
import com.example.photosearchapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface PhotoDetailsRepository {

    suspend fun getDetails(
        id: Int,
        apiKey: String
    ): Flow<Resource<Photo>>

}










