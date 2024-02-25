package com.example.base.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import com.example.base.R
import com.example.domain.models.ImageModel

@Composable
fun PixListItem(pixImage: ImageModel, modifier: Modifier, onClick: () -> Unit) {
    Card(
        onClick = onClick,
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
                pixImage.tags.sortedByDescending{ it.length }.forEach {
                    SuggestionChip(
                        onClick = { },
                        label = { Text(it, style = MaterialTheme.typography.labelSmall) }
                    )
                }
            }
        }
    }
}
