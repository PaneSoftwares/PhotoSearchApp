package com.example.photosearchapp.features.search.presentation

import com.example.photosearchapp.features.main.domain.model.Photo

sealed class SearchUiEvents {
    data class Refresh(val type: String) : SearchUiEvents()
    data class OnPaginate(val type: String) : SearchUiEvents()
    data class OnSearchQueryChanged(val query: String) : SearchUiEvents()
    data class OnSearchedItemClick(val photo: Photo) : SearchUiEvents()
}