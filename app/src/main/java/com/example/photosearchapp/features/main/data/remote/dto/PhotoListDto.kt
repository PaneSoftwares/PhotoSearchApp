package com.example.photosearchapp.features.main.data.remote.dto

import com.example.photosearchapp.features.main.domain.model.Photo
import com.google.gson.annotations.SerializedName

data class PhotoListDto(
    @SerializedName("total_results") var totalResults: Int? = null,
    @SerializedName("page") var page: Int? = null,
    @SerializedName("per_page") var perPage: Int? = null,
    @SerializedName("photos") var photos: ArrayList<Photo> = arrayListOf(),
    @SerializedName("next_page") var nextPage: String? = null
)