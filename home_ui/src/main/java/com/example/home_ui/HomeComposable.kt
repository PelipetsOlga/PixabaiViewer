package com.example.home_ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import com.example.base.R
import com.example.base.composables.HorizontalPixListItem
import com.example.base.composables.VerticalPixListItem
import com.example.domain.models.ImageModel
import kotlin.math.absoluteValue

@Composable
internal fun HomeComposable(
    itemClick: (ImageModel) -> Unit,
    viewModel: HomeViewModel
) {
    val configuration = LocalConfiguration.current

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            HomeVerticalComposable(itemClick, viewModel)
        }
        else -> {
            HomeHorizontalComposable(itemClick, viewModel)
        }
    }
}

@Composable
internal fun HomeVerticalComposable(
    itemClick: (ImageModel) -> Unit,
    viewModel: HomeViewModel
) {
    val results by viewModel.results.collectAsState(listOf())
    LazyHorizontalStaggeredGrid(
        rows = StaggeredGridCells.Adaptive(dimensionResource(id = R.dimen.dp_96)),
        horizontalItemSpacing = dimensionResource(id = R.dimen.dp_8),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp_8)),
        content = {
            items(results.size, key = { index -> results[index].id }) { index ->
                val pixImage = results[index]
                VerticalPixListItem(
                    pixImage = pixImage,
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio((pixImage.previewWidth.absoluteValue.toFloat() / pixImage.previewHeight.absoluteValue.toFloat())),
                    onClick = itemClick,
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

@Composable
internal fun HomeHorizontalComposable(
    itemClick: (ImageModel) -> Unit,
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
                HorizontalPixListItem(
                    pixImage = pixImage,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio((pixImage.previewWidth.absoluteValue.toFloat() / pixImage.previewHeight.absoluteValue.toFloat())),
                    onClick = itemClick,
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



