package com.example.photosearchapp.features.main.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.photosearchapp.features.photo_details.presentation.PhotoDetailsScreen
import com.example.photosearchapp.features.photo_details.presentation.PhotoDetailsScreenEvents
import com.example.photosearchapp.features.photo_details.presentation.PhotoDetailsViewModel
import com.example.photosearchapp.features.photo_details.presentation.SomethingWentWrong
import com.example.photosearchapp.features.search.presentation.SearchScreen
import com.example.photosearchapp.navigation.Route
import com.example.photosearchapp.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                val mainViewModel = hiltViewModel<MainViewModel>()
                val mainUiState = mainViewModel.mainUiState.collectAsState().value

                Navigation(
                    mainUiState = mainUiState,
                    onEvent = mainViewModel::onEvent
                )
            }
        }
    }
}

@Composable
fun Navigation(
    mainUiState: MainUiState,
    onEvent: (MainUiEvents) -> Unit
) {
    val navController = rememberNavController()

    val photoDetailsViewModel = hiltViewModel<PhotoDetailsViewModel>()
    val photoDetailsScreenState =
        photoDetailsViewModel.photoDetailsScreenState.collectAsState().value

    NavHost(
        navController = navController,
        startDestination = Route.PHOTO_MAIN_SCREEN
    ) {

        composable(Route.PHOTO_MAIN_SCREEN) {
            PhotoMainScreen(
                navController = navController,
                mainUiState = mainUiState,
                onEvent = onEvent
            )
        }

        composable(Route.SEARCH_SCREEN) {
            SearchScreen(
                navController = navController,
                mainUiState = mainUiState,
            )
        }

        composable(
            "${Route.PHOTO_DETAILS_SCREEN}?id={id}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType },
            )
        ) {
            val id = it.arguments?.getInt("id") ?: 0

            LaunchedEffect(key1 = true) {
                photoDetailsViewModel.onEvent(
                    PhotoDetailsScreenEvents.SetDataAndLoad(
                        id = id
                    )
                )
            }

            if (photoDetailsScreenState.photo != null) {
                PhotoDetailsScreen(
                    photo = photoDetailsScreenState.photo
                )
            } else {
                SomethingWentWrong()
            }
        }
    }
}