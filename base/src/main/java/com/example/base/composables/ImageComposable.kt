package com.example.base.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ImageComponent(imageUrl: String, contentDescription: String?, modifier: Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        error = ColorPainter(MaterialTheme.colorScheme.errorContainer),
        placeholder = ColorPainter(MaterialTheme.colorScheme.secondaryContainer),
        contentDescription = contentDescription,
        modifier = modifier
    )
}