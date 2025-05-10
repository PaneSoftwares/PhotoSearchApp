package com.example.photosearchapp.ui.elements

//import androidx.compose.material.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.photosearchapp.R
import com.example.photosearchapp.features.search.presentation.SearchScreenState
import com.example.photosearchapp.navigation.Route
import com.example.photosearchapp.ui.theme.BigRadius
import com.example.photosearchapp.ui.theme.font

@Composable
fun NonFocusedTopBar(
    toolbarOffsetHeightPx: Int,
    navController: NavController,
) {

    Box(
        modifier = Modifier
            .background(Color.Transparent)
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .height(BigRadius.dp)
            .offset { IntOffset(x = 0, y = toolbarOffsetHeightPx) }
    ) {
        NonFocusedSearchBar(
            modifier = Modifier
                .height(50.dp)
                .clickable {
                    navController.navigate(Route.SEARCH_SCREEN)
                }
                .padding(horizontal = 6.dp),
            placeholderText = stringResource(R.string.search_image),
        )
    }
}

@Composable
fun FocusedTopBar(
    toolbarOffsetHeightPx: Int,
    searchScreenState: SearchScreenState,
    onSearch: (String) -> Unit = {}
) {

    Box(
        modifier = Modifier
            .background(Color.Transparent)
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .height(BigRadius.dp)
            .offset { IntOffset(x = 0, y = toolbarOffsetHeightPx) }
    ) {
        TopSearchBar(
            leadingIcon = {
                Icon(
                    Icons.Rounded.Search,
                    null,
                    tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                )
            },
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .height(50.dp),
            placeholderText = stringResource(R.string.search_image),
            searchScreenState = searchScreenState
        ) {
            onSearch(it)
        }
    }
}


@Composable
fun NavTopBar(
    title: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit = {},
    actions: @Composable () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .background(Color.Transparent)
            .height(BigRadius.dp)
    ) {
        if (canNavigateBack) {
            TopAppBar(
                title = {
                    Text(
                        text = title, color = MaterialTheme.colorScheme.onBackground,
                        fontFamily = font,
                        fontSize = 17.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },

                backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                actions = { actions() },
                navigationIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                },

                )
        } else {
            TopAppBar(
                elevation = 4.dp,
                backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                title = {
                    Text(
                        text = title, color = MaterialTheme.colorScheme.onBackground,
                        fontFamily = font,
                        fontSize = 17.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },

                actions = { actions() },
            )
        }
    }
}
