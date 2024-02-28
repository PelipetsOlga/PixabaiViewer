package com.example.home_ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.base.R
import com.example.base.composables.HorizontalPixListItem
import com.example.base.composables.VerticalPixListItem
import com.example.domain.models.ImageModel
import kotlin.math.absoluteValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeComposable(
    itemClick: (ImageModel) -> Unit,
    viewModel: HomeViewModel
) {
    val configuration = LocalConfiguration.current
    val loading by viewModel.loading.collectAsState(false)
    val searchText by viewModel.searchText.collectAsState()

    Box(contentAlignment = Alignment.Center) {
        Column() {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(
                        bottom = dimensionResource(id = R.dimen.dp_8),
                    )
            ) {
                SearchBar(
                    query = searchText,
                    onQueryChange = viewModel::onSearchTextChange,
                    onSearch = viewModel::onSearchTextChange,
                    active = false,
                    onActiveChange = { },
                    trailingIcon = {
                        if (searchText.isNotEmpty()) {
                            Icon(
                                Icons.Default.Clear,
                                contentDescription = stringResource(id = R.string.cd_clear),
                                modifier = Modifier.clickable { viewModel.onSearchTextChange("") }
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = dimensionResource(id = R.dimen.dp_16),
                        )
                ) {}
            }
            when (configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> {
                    HomeVerticalComposable(itemClick, viewModel)
                }

                else -> {
                    HomeHorizontalComposable(itemClick, viewModel)
                }
            }
        }
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.width(dimensionResource(id = R.dimen.dp_64)),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }
    }
}

@Composable
internal fun HomeVerticalComposable(
    itemClick: (ImageModel) -> Unit,
    viewModel: HomeViewModel
) {
    val pagingItems: LazyPagingItems<ImageModel> =
        viewModel.results.collectAsLazyPagingItems()

    LazyHorizontalStaggeredGrid(
        rows = StaggeredGridCells.Adaptive(dimensionResource(id = R.dimen.dp_96)),
        horizontalItemSpacing = dimensionResource(id = R.dimen.dp_8),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp_8)),
        content = {
            items(pagingItems.itemCount, key = pagingItems.itemKey { it.largeImageURL }) { index ->
                val pixImage = pagingItems[index] ?: return@items
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
    val pagingItems: LazyPagingItems<ImageModel> =
        viewModel.results.collectAsLazyPagingItems()

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(dimensionResource(id = R.dimen.dp_160)),
        verticalItemSpacing = dimensionResource(id = R.dimen.dp_8),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp_8)),
        content = {
            items(pagingItems.itemCount, key = pagingItems.itemKey { it.largeImageURL }) { index ->
                val pixImage = pagingItems[index] ?: return@items
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
