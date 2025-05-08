package com.example.photosearchapp.features.photo_details.presentation

sealed class PhotoDetailsScreenEvents {

    data class SetDataAndLoad(
        val id: Int,
    ) : PhotoDetailsScreenEvents()

    data object Refresh : PhotoDetailsScreenEvents()
}