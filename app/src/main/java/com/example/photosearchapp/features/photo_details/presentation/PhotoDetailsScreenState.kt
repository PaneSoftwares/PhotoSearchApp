package com.example.photosearchapp.features.photo_details.presentation

import com.example.photosearchapp.features.main.domain.model.Photo

data class PhotoDetailsScreenState(

    val isLoading: Boolean = false,

    val photo: Photo? = null,

    )