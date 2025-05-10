package com.example.photosearchapp.features.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photosearchapp.features.main.data.remote.api.PhotoApi.Companion.API_KEY
import com.example.photosearchapp.features.main.domain.repository.MainRepository
import com.example.photosearchapp.features.search.domain.repository.SearchRepository
import com.example.photosearchapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val searchRepository: SearchRepository,
) : ViewModel() {

    private val _searchScreenState = MutableStateFlow(SearchScreenState())
    val searchScreenState = _searchScreenState.asStateFlow()

    private var searchJob: Job? = null

    private var loadJob: Job? = null

    fun onEvent(event: SearchUiEvents) {
        when (event) {

            is SearchUiEvents.OnSearchedItemClick -> {
                viewModelScope.launch {
                    mainRepository.insertItem(event.photo)
                }
            }

            is SearchUiEvents.OnSearchQueryChanged -> {
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(1000L)

                    _searchScreenState.update {
                        it.copy(
                            searchQuery = event.query,
                            searchList = emptyList(),
                            searchPage = 1,
                        )
                    }

                    loadSearchList()
                }
            }

            is SearchUiEvents.OnPaginate -> {
                loadJob?.cancel()
                loadJob = viewModelScope.launch {
                    delay(1000L)
                    _searchScreenState.update {
                        it.copy(
                            searchPage = searchScreenState.value.searchPage + 1
                        )
                    }
                    loadSearchList()
                }
            }

            is SearchUiEvents.Refresh -> TODO()
        }
    }

    private fun loadSearchList() {

        viewModelScope.launch {

            searchRepository
                .getSearchList(
                    fetchFromRemote = true,
                    query = searchScreenState.value.searchQuery,
                    page = searchScreenState.value.searchPage,
                    apiKey = API_KEY
                )
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { photoList ->
                                _searchScreenState.update {
                                    it.copy(
                                        searchList = searchScreenState.value.searchList + photoList
                                    )
                                }

                            }
                        }

                        is Resource.Error -> Unit

                        is Resource.Loading -> {
                            _searchScreenState.update {
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

