package com.example.photosearchapp.features.main.data.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.photosearchapp.features.main.domain.model.Src

@Entity
data class PhotoEntity(
    @PrimaryKey val id: Int,

    val width: Int,
    val height: Int,
    val url: String?,
    val photographer: String?,
    val photographerUrl: String?,
    val photographerId: Int,
    val avgColor: String?,
    @Embedded
    val src: Src,
    val liked: Boolean,
    val alt: String?
)