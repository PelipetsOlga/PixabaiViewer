package com.example.data.api

import com.example.data.models.PixResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class PixApiHelperImpl @Inject constructor(private val api: PixApi) : PixApiHelper {

    override fun search(keyword: String): Flow<PixResponse> {
        return flow { emit(api.getSearchResults(keyword = keyword)) }
    }
}
