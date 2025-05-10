package com.example.photosearchapp.ui.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.photosearchapp.ui.theme.Radius
import com.example.photosearchapp.ui.theme.RadiusContainer
import com.example.photosearchapp.ui.theme.font

@Composable
fun PullToReloadView(
    background: Int,
    title: String
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        contentPadding = PaddingValues(top = 10.dp),
        columns = GridCells.Fixed(1)
    ) {
        items(1) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = 14.dp, start = 8.dp, end = 8.dp
                    )
                    .clip(RoundedCornerShape(RadiusContainer.dp))
            ) {

                if(background != 0){
                Box(
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxSize()
                        .padding(6.dp)
                        .paint(painterResource(id = background))
                        .clip(RoundedCornerShape(Radius.dp))
                )}

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Light,
                    fontFamily = font,
                    fontSize = 16.sp,
                    modifier = (Modifier
                        .fillMaxSize()
                        .padding(6.dp)
                            )
                )
            }
        }
    }
}