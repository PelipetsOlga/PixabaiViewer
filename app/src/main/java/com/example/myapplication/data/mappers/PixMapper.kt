package com.example.myapplication.data.mappers

import com.example.myapplication.data.models.PixImage
import com.example.myapplication.domain.models.ImageModel

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
        tags = tags.split(", ")
    )
}
