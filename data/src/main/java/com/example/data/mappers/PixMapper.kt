package com.example.data.mappers

import com.example.domain.models.ImageModel
import com.example.data.models.PixImage

internal fun PixImage.toDomain(): ImageModel {
    return ImageModel(
        id = id,
        previewURL = previewURL,
        previewHeight = previewHeight,
        previewWidth = previewWidth,
        largeImageURL = largeImageURL,
        imageWidth = imageWidth,
        imageHeight = imageHeight,
        views = views,
        downloads = downloads,
        likes = likes,
        comments = comments,
        user = user,
        userImageURL = userImageURL,
        tags = tags.split(", ").filter { it.isNotBlank() }
    )
}
