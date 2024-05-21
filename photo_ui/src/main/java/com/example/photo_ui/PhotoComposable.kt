package com.example.photo_ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.base.R
import com.example.base.composables.HorizontalBigPhoto
import com.example.base.composables.VerticalBigPhoto
import com.example.domain.models.emptyImageModel
import io.cux.analytics_sdk.composable.UnmaskComposableOnDispose
import io.cux.analytics_sdk.composable.maskElement
import io.cux.analytics_sdk.composable.rememberComposableId

@Composable
internal fun PhotoComposable(
    viewModel: PhotoViewModel,
    backClick: () -> Unit
) {
    val image by viewModel.image.collectAsState(emptyImageModel)
    val configuration = LocalConfiguration.current
    val elementId = rememberComposableId()

    Box(modifier = Modifier.fillMaxSize()) {
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(0.dp)
        ) {
            when (configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> HorizontalBigPhoto(image)
                else -> VerticalBigPhoto(image)
            }
        }
        val backgroundColor = MaterialTheme.colorScheme.secondaryContainer
        val iconTintColor = MaterialTheme.colorScheme.onSecondaryContainer
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.dp_16))
                .align(Alignment.TopStart)
        ) {
            IconButton(onClick = backClick) {
                Icon( modifier = Modifier
                    .drawBehind {
                        drawCircle(
                            color = backgroundColor,
                            radius = this.size.maxDimension
                        )
                    }.maskElement(elementId),
                    tint = iconTintColor,
                    painter = painterResource(id = R.drawable.baseline_arrow_back),
                    contentDescription = stringResource(id = R.string.cd_back)
                )
            }
        }
    }

    UnmaskComposableOnDispose(elementId)
}
