package com.example.data.models

import com.example.domain.models.ImageModel

internal data class PixImage(
    val id: Int,
    val pageURL: String,
    val type: String,
    val tags: String,
    val previewURL: String,
    val previewWidth: Int,
    val previewHeight: Int,
    val webformatURL: String,
    val webformatWidth: Int,
    val webformatHeight: Int,
    val largeImageURL: String,
    val imageWidth: Int,
    val imageHeight: Int,
    val imageSize: Int,
    val views: Int,
    val downloads: Int,
    val collections: Int,
    val likes: Int,
    val comments: Int,
    val user_id: Int,
    val user: String,
    val userImageURL: String
)

internal val emptyPixImage = PixImage(
    id = 0,
    pageURL = "",
    type = "",
    tags = "",
    previewURL = "",
    previewWidth = 0,
    previewHeight = 0,
    webformatURL = "",
    webformatWidth = 0,
    webformatHeight = 0,
    largeImageURL = "",
    imageWidth = 0,
    imageHeight = 0,
    imageSize = 0,
    views = 0,
    downloads = 0,
    collections = 0,
    likes = 0,
    comments = 0,
    user_id = 0,
    user = "",
    userImageURL = ""
)

internal val mockPixImage = PixImage(
    id = 1,
    pageURL = "url",
    type = "type",
    tags = "lemon, apple, fruits",
    previewURL = "https://cdn.pixabay.com/photo/2015/08/13/20/06/flower-887443_150.jpg",
    previewWidth = 150,
    previewHeight = 150,
    webformatURL = "url",
    webformatWidth = 300,
    webformatHeight = 300,
    largeImageURL = "https://pixabay.com/get/g6dbfac0adc48e43a983f0558dbc2fec343a9f55183f7136759436377675eff99639bedf49098db0d26ede93c6f596dd7683886ac765d5cc0811be784ef3c8b5b_1280.jpg",
    imageWidth = 350,
    imageHeight = 350,
    imageSize = 0,
    views = 30,
    downloads = 20,
    collections = 0,
    likes = 100,
    comments = 10,
    user_id = 100,
    user = "Gardener",
    userImageURL = "https://cdn.pixabay.com/user/2015/11/27/06-58-54-609_250x250.jpg"
)
