package com.example.photosearchapp.features.photo_details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photosearchapp.features.main.data.remote.api.PhotoApi.Companion.API_KEY
import com.example.photosearchapp.features.main.domain.repository.MainRepository
import com.example.photosearchapp.features.photo_details.domain.repository.PhotoDetailsRepository
import com.example.photosearchapp.util.Resource

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoDetailsViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val detailsRepository: PhotoDetailsRepository,
) : ViewModel() {

    private val _photoDetailsScreenState = MutableStateFlow(PhotoDetailsScreenState())
    val photoDetailsScreenState = _photoDetailsScreenState.asStateFlow()

    fun onEvent(event: PhotoDetailsScreenEvents) {
        when (event) {

            is PhotoDetailsScreenEvents.Refresh -> {
                _photoDetailsScreenState.update {
                    it.copy(
                        isLoading = true
                    )
                }

                startLoad()
            }

            is PhotoDetailsScreenEvents.SetDataAndLoad -> {
                _photoDetailsScreenState.update {
                    it.copy()
                }

                startLoad(
                    id = event.id,
                )
            }
        }
    }

    private fun startLoad(
        id: Int = photoDetailsScreenState.value.photo?.id ?: 0,
    ) {

        loadPhotoItem(
            id = id
        ) {
            loadDetails()
        }
    }

    private fun loadPhotoItem(
        id: Int,
        onFinished: () -> Unit
    ) {
        viewModelScope.launch {
            _photoDetailsScreenState.update {
                it.copy(
                    photo = mainRepository.getItem(
                        id = id,
                    )
                )
            }
            onFinished()
        }
    }

    private fun loadDetails() {

        viewModelScope.launch {

            detailsRepository
                .getDetails(
                    id = photoDetailsScreenState.value.photo?.id ?: 0,
                    apiKey = API_KEY
                )
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { photo ->
                                _photoDetailsScreenState.update {
                                    it.copy(
                                        photo = photoDetailsScreenState.value.photo?.copy(),
                                    )
                                }
                            }
                        }

                        is Resource.Error -> Unit

                        is Resource.Loading -> {
                            _photoDetailsScreenState.update {
                                it.copy(
                                    isLoading = result.isLoading
                                )
                            }
                        }
                    }
                }
        }
    }

}




