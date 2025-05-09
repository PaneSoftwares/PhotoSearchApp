package com.example.photosearchapp.features.search.domain.repository

import com.example.photosearchapp.features.main.domain.model.Photo
import com.example.photosearchapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun getSearchList(
        fetchFromRemote: Boolean,
        query: String,
        page: Int,
        apiKey: String
    ): Flow<Resource<List<Photo>>>
}