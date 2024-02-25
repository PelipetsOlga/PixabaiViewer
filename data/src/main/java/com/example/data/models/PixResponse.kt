package com.example.myapplication.data.models

import com.example.myapplication.data.models.PixImage

class PixResponse(
    val total: Int,
    val totalHits: Int,
    val hits: List<PixImage>
)
