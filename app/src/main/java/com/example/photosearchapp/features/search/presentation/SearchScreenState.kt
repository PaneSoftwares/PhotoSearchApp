package com.example.photosearchapp.features.search.presentation

import com.example.photosearchapp.features.main.domain.model.Photo

data class SearchScreenState(
    val searchQuery: String = "",
    val searchPage: Int = 1,
    val searchList: List<Photo> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
)