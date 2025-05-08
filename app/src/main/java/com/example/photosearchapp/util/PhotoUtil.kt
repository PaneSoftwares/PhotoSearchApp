package com.example.photosearchapp.util

import com.example.photosearchapp.features.main.domain.model.Src

class PhotoUtil {
    companion object {
        fun getBestImage(src: Src) : String {
            return if (src.large2x.isNotEmpty()) {
                src.large2x
            } else if (src.large.isNotEmpty()) {
                src.large
            } else if (src.original.isNotEmpty()) {
                src.original
            } else {
                src.medium
            }
        }
    }
}