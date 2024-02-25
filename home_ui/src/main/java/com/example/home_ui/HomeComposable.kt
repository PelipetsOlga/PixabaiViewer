package com.example.home_ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavController
import com.example.base.R
import com.example.base.composables.PixListItem
import kotlin.math.absoluteValue

@Composable
internal fun HomeComposable(
    navController: NavController,
    viewModel: HomeViewModel
) {
    val results by viewModel.results.collectAsState(listOf())

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(dimensionResource(id = R.dimen.dp_160)),
        verticalItemSpacing = dimensionResource(id = R.dimen.dp_8),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp_8)),
        content = {
            items(results.size, key = { index -> results[index].id }) { index ->
                val pixImage = results[index]
                PixListItem(
                    pixImage = pixImage,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio((pixImage.previewWidth.absoluteValue.toFloat() / pixImage.previewHeight.absoluteValue.toFloat())),
                    onClick = { navController.navigate(com.example.home_ui.R.id.action_to_photo) },
                )
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = dimensionResource(id = R.dimen.dp_8),
                horizontal = dimensionResource(id = R.dimen.dp_8),
            )
    )
}



