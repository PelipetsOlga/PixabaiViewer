package com.example.myapplication.data.api

import com.example.myapplication.data.models.PixResponse
import kotlinx.coroutines.flow.Flow

 interface PixApiHelper {
    fun search(keyword: String): Flow<PixResponse>
}
