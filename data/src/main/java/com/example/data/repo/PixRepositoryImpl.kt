package com.example.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.api.PixApi
import com.example.domain.models.ImageModel
import com.example.domain.repo.PixRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class PixRepositoryImpl @Inject constructor(
    private val api: PixApi
) : PixRepository {

    override fun getSearchResultStream(query: String): Flow<PagingData<ImageModel>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { PixPagingSource(api, query) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }
}
