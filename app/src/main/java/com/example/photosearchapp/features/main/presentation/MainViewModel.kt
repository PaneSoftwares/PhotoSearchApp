package com.example.photosearchapp.features.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photosearchapp.features.main.domain.repository.MainRepository
import com.example.photosearchapp.util.Constants
import com.example.photosearchapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
) : ViewModel() {

    private val _mainUiState = MutableStateFlow(MainUiState())
    val mainUiState = _mainUiState.asStateFlow()

    init {
        loadHistory()
    }

    fun onEvent(event: MainUiEvents) {
        when (event) {
            is MainUiEvents.Refresh -> {
                _mainUiState.update {
                    it.copy(
                        isLoading = true
                    )
                }

                when (event.type) {
                    Constants.historyScreen -> {
                        loadHistory()
                    }
                }
            }

            is MainUiEvents.OnPaginate -> {

            }

        }
    }

    private fun loadHistory() {
        viewModelScope.launch {

            mainRepository
                .getPhotoList(
                )
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { photoList ->

                                _mainUiState.update {
                                    it.copy(
                                        historyPhotoList = photoList,
                                    )
                                }
                            }
                        }

                        is Resource.Error -> Unit

                        is Resource.Loading -> {
                            _mainUiState.update {
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






