package com.example.data.api

import com.example.data.models.PixResponse
import kotlinx.coroutines.flow.Flow

internal interface PixApiHelper {
    fun search(keyword: String): Flow<PixResponse>
}
