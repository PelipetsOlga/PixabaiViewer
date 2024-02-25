package com.example.data.models

internal class PixResponse(
    val total: Int,
    val totalHits: Int,
    val hits: List<PixImage>
)
