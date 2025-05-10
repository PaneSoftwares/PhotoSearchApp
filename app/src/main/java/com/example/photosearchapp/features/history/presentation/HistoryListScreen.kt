package com.example.photosearchapp.features.history.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.photosearchapp.R
import com.example.photosearchapp.features.main.presentation.MainUiEvents
import com.example.photosearchapp.features.main.presentation.MainUiState
import com.example.photosearchapp.navigation.BottomNavRoute
import com.example.photosearchapp.ui.elements.PullToReloadView
import com.example.photosearchapp.util.Constants.historyScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HistoryListScreen(
    selectedItem: MutableState<Int>,
    navController: NavController,
    bottomBarNavController: NavHostController,
    mainUiState: MainUiState,
    onEvent: (MainUiEvents) -> Unit
) {
    BackHandler(
        enabled = true
    ) {
        selectedItem.value = 0
        bottomBarNavController.navigate(BottomNavRoute.DEFAULT_SCREEN)
    }

    val photoList = mainUiState.historyPhotoList

    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }

    fun refresh() = refreshScope.launch {
        refreshing = true
        delay(1000)

        onEvent(MainUiEvents.Refresh(type = historyScreen))
        refreshing = false
    }

    val refreshState = rememberPullRefreshState(refreshing, ::refresh)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .pullRefresh(refreshState)
    ) {
        if (photoList.isEmpty()) {
            PullToReloadView(
                R.drawable.history_image,
                stringResource(R.string.pull_down_refresh)
            )
        } else {

            val listState = rememberLazyGridState()

            LazyVerticalGrid(
                state = listState,
                contentPadding = PaddingValues(top = 20.dp),
                columns = GridCells.Adaptive(180.dp),
            ) {
                items(photoList.size) { i ->

                    HistoryPhotoItem(
                        photo = photoList[i],
                        navController = navController,
                    )
                }
            }
        }

        PullRefreshIndicator(
            refreshing,
            refreshState,
            Modifier
                .align(Alignment.TopCenter)
                .padding(top = 8.dp)
        )
    }
}