package com.example.domain.repo

import androidx.paging.PagingData
import com.example.domain.models.ImageModel
import kotlinx.coroutines.flow.Flow

interface PixRepository {
    fun getSearchResultStream(query: String): Flow<PagingData<ImageModel>>
}
