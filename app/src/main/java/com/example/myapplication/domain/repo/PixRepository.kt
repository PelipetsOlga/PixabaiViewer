package com.example.myapplication.domain.repo

import com.example.myapplication.domain.models.ImageModel
import com.example.myapplication.domain.models.Result
import kotlinx.coroutines.flow.Flow

interface PixRepository {
    fun getSearchResults(keyword: String): Flow<Result<List<ImageModel>>>
}
