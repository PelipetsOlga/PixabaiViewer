package com.example.data.repo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.api.PixApi
import com.example.data.mappers.toDomain
import com.example.domain.models.ImageModel

private const val UNSPLASH_STARTING_PAGE_INDEX = 1
private const val PER_PAGE = 20

internal class PixPagingSource(
    private val api: PixApi,
    private val query: String
) : PagingSource<Int, ImageModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageModel> {
        val page = params.key ?: UNSPLASH_STARTING_PAGE_INDEX
        return try {
            val response = api.getSearchResults(keyword = query, page = page.toString())
            val photos = response.hits.map { it.toDomain() }
            LoadResult.Page(
                data = photos,
                prevKey = if (page == UNSPLASH_STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (page >= response.total / PER_PAGE) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ImageModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}
