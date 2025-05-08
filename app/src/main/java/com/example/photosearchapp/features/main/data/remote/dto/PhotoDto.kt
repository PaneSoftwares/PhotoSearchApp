package com.example.photosearchapp.features.main.data.remote.dto

import com.example.photosearchapp.features.main.domain.model.Src

data class PhotoDto(
    val id: Int? = null,
    val width: Int? = null,
    val height: Int? = null,
    var url: String? = null,
    val photographer: String? = null,
    val photographerUrl: String? = null,
    val photographerId: Int? = null,
    val avgColor: String? = null,
    val src: Src,
    val liked: Boolean? = null,
    val alt: String? = null,
)