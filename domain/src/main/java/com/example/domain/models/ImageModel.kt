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

val imageModelMock = ImageModel(
    id = 1,
    user = "Gardener",
    likes = 100,
    tags = listOf("lemon", "apple"),
    previewURL = "https://cdn.pixabay.com/photo/2015/08/13/20/06/flower-887443_150.jpg",
    largeImageURL = "https://pixabay.com/get/g6dbfac0adc48e43a983f0558dbc2fec343a9f55183f7136759436377675eff99639bedf49098db0d26ede93c6f596dd7683886ac765d5cc0811be784ef3c8b5b_1280.jpg",
    comments = 10,
    views = 30,
    previewWidth = 150,
    previewHeight = 150,
    imageHeight = 350,
    imageWidth = 350,
    downloads = 20,
    userImageURL = "https://cdn.pixabay.com/user/2015/11/27/06-58-54-609_250x250.jpg"
)