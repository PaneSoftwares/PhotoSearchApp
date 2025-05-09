package com.example.photosearchapp.features.main.presentation

import com.example.photosearchapp.features.main.domain.model.Photo

data class MainUiState(

    val historyPhotoPage: Int = 1,
    val searchPhotoPage: Int = 1,

    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,

    val searchPhotoList: List<Photo> = emptyList(),
    val historyPhotoList: List<Photo> = emptyList(),

    )