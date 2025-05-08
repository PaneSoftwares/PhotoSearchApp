package com.example.photosearchapp.features.main.data.mapper

import com.example.photosearchapp.features.main.data.local.PhotoEntity
import com.example.photosearchapp.features.main.data.remote.dto.PhotoDto
import com.example.photosearchapp.features.main.domain.model.Photo
import com.example.photosearchapp.util.Constants

fun PhotoEntity.toPhoto(
    id: Int
): Photo {
    return Photo(
        id = id,
        width = width,
        height = height,

        url = url,
        photographer = photographer,
        photographerUrl = photographerUrl,
        photographerId = photographerId,
        avgColor = avgColor,
        src = src,
        liked = liked,
        alt = alt,
    )
}

fun PhotoDto.toPhotoEntity(
    
): PhotoEntity {
    return PhotoEntity(
        id = id ?: 0,
        width = width ?: 0,
        height = height ?: 0,
        url = url ?: Constants.unavailable,
        photographer = photographer ?: Constants.unavailable,
        photographerUrl = photographerUrl ?: Constants.unavailable,
        photographerId = photographerId ?: 0,
        avgColor = avgColor ?: Constants.unavailable,
        src = src,
        liked = liked ?: false,
        alt = alt ?: Constants.unavailable,
    )
}


fun PhotoDto.toPhoto(
): Photo {
    return Photo(
        id = id ?: 0,
        width = width ?: 0,
        height = height ?: 0,
        url = url ?: Constants.unavailable,
        photographer = photographer ?: Constants.unavailable,
        photographerUrl = photographerUrl ?: Constants.unavailable,
        photographerId = photographerId ?: 0,
        avgColor = avgColor ?: Constants.unavailable,
        src = src,
        liked = liked ?: false,
        alt = alt ?: Constants.unavailable,
    )
}

fun Photo.toPhotoEntity(): PhotoEntity {
    return PhotoEntity(
        id = id,
        width = width,
        height = height,

        url = url,
        photographer = photographer,
        photographerUrl = photographerUrl,
        photographerId = photographerId,
        avgColor = avgColor,
        src = src,
        liked = liked,
        alt = alt,
    )
}






