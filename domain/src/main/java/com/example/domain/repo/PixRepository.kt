package com.example.domain.repo

import com.example.domain.models.ImageModel
import com.example.domain.models.Result
import kotlinx.coroutines.flow.Flow

interface PixRepository {
    fun getSearchResults(keyword: String): Flow<Result<List<ImageModel>>>
}
