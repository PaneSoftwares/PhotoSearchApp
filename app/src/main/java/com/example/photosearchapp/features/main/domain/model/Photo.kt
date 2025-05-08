package com.example.photosearchapp.features.main.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Photo(
    val id: Int,
    val width: Int,
    val height: Int,
    val url: String?,
    val photographer: String?,
    val photographerUrl: String?,
    val photographerId: Int,
    val avgColor: String?,
    val src: Src,
    val liked: Boolean,
    val alt: String?
) : Parcelable

@Parcelize
data class Src(
    val original: String,
    val large2x: String,
    val large: String,
    val medium: String,
    val small: String,
    val portrait: String,
    val landscape: String,
    val tiny: String
) : Parcelable