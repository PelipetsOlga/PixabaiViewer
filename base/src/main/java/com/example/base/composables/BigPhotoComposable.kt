package com.example.base.composables

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import com.example.base.R
import com.example.domain.models.ImageModel

@Composable
fun VerticalBigPhoto(pixImage: ImageModel) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        ImageComponent(
            imageUrl = pixImage.previewURL,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        Description(pixImage)
    }
}

@Composable
fun HorizontalBigPhoto(pixImage: ImageModel) {
    Row(Modifier.fillMaxHeight(), verticalAlignment = Alignment.Top) {
        ImageComponent(
            imageUrl = pixImage.previewURL,
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
        )
        Description(pixImage)
    }
}

@Composable
fun Description(image: ImageModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = dimensionResource(id = R.dimen.dp_8),
                horizontal = dimensionResource(id = R.dimen.dp_16),
            )
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                bottom = dimensionResource(id = R.dimen.dp_16),
                top = dimensionResource(id = R.dimen.dp_8)
            )
        ) {
            ImageComponent(
                imageUrl = image.userImageURL,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = dimensionResource(id = R.dimen.dp_16))
                    .width(dimensionResource(id = R.dimen.dp_64))
                    .height(dimensionResource(id = R.dimen.dp_64))
                    .clip(shape = CircleShape)
            )
            Text(
                text = image.user,
                textAlign = TextAlign.Start,
                maxLines = 1,
                style = MaterialTheme.typography.headlineMedium,
            )
        }
        ImageChip(imageId = R.drawable.noun_like_888708, text = "${image.likes}")
        ImageChip(imageId = R.drawable.baseline_download_24, text = "${image.downloads}")
        ImageChip(imageId = R.drawable.baseline_comment_24, text = "${image.comments}")
        image.tags.sortedByDescending { it.length }.forEach {
            TagChip(it)
        }
    }
}

