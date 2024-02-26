package com.example.base.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import com.example.base.R
import com.example.domain.models.ImageModel

@Composable
fun VerticalPixListItem(pixImage: ImageModel, modifier: Modifier, onClick: (ImageModel) -> Unit) {
    Card(
        onClick = { onClick(pixImage) },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
    ) {
        Row(Modifier.fillMaxHeight(), verticalAlignment = Alignment.Top) {
            ImageComponent(
                imageUrl = pixImage.previewURL,
                contentDescription = null,
                modifier = modifier,
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = dimensionResource(id = R.dimen.dp_8),
                        horizontal = dimensionResource(id = R.dimen.dp_16),
                    )
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = pixImage.user,
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleMedium,
                )
                pixImage.tags.sortedByDescending { it.length }.forEach {
                    TagChip(text = it)
                }
            }
        }
    }
}

@Composable
fun HorizontalPixListItem(pixImage: ImageModel, modifier: Modifier, onClick: (ImageModel) -> Unit) {
    Card(
        onClick = { onClick(pixImage) },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
    ) {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
            ImageComponent(
                imageUrl = pixImage.previewURL,
                contentDescription = null,
                modifier = modifier,
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = dimensionResource(id = R.dimen.dp_8),
                        horizontal = dimensionResource(id = R.dimen.dp_16),
                    )
            ) {
                Text(
                    text = pixImage.user,
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleMedium,
                )
                pixImage.tags.sortedByDescending { it.length }.forEach {
                    TagChip(text = it)
                }
            }
        }
    }
}
