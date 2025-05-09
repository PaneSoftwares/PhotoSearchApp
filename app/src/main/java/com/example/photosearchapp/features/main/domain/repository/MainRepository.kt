package com.example.photosearchapp.features.main.domain.repository

import com.example.photosearchapp.features.main.domain.model.Photo
import com.example.photosearchapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun insertItem(photo: Photo)

    suspend fun getItem(
        id: Int
    ): Photo

    suspend fun getPhotoList(
    ): Flow<Resource<List<Photo>>>
}