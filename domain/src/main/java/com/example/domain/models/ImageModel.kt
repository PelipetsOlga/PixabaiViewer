package com.example.domain.models

data class ImageModel(
    val id: Int,
    val tags: List<String>,
    val previewURL: String,
    val previewWidth: Int,
    val previewHeight: Int,
    val largeImageURL: String,
    val imageWidth: Int,
    val imageHeight: Int,
    val views: Int,
    val downloads: Int,
    val likes: Int,
    val comments: Int,
    val user: String,
    val userImageURL: String
)
