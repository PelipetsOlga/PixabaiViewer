package com.example.base.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.example.base.R

@Composable
internal fun ImageChip(imageId: Int, text: String) {
    val iconTintColor = MaterialTheme.colorScheme.onSurfaceVariant
    SuggestionChip(
        onClick = {},
        label = {
            Row {
                Icon(
                    painterResource(imageId),
                    contentDescription = "",
                    tint = iconTintColor,
                )
                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.dp_8)))
                Text(text, style = MaterialTheme.typography.bodyLarge)
            }
        }
    )
}

@Composable
internal fun TagChip(text: String) {
    SuggestionChip(
        onClick = {},
        label = { Text(text, style = MaterialTheme.typography.labelLarge) }
    )
}

