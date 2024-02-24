package com.example.myapplication.data.repo

import com.example.myapplication.data.api.PixApiHelper
import com.example.myapplication.data.mappers.toDomain
import com.example.myapplication.domain.models.ImageModel
import com.example.myapplication.domain.models.NetworkError
import com.example.myapplication.domain.models.Result
import com.example.myapplication.domain.repo.PixRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
 class PixRepositoryImpl @Inject constructor(
    private val apiHelper: PixApiHelper
) : PixRepository {
    override fun getSearchResults(keyword: String): Flow<Result<List<ImageModel>>> {
        return apiHelper.search(keyword)
            .map { response -> response.hits.map { it.toDomain() } }
            .map { Result.withValue(it) }
            .catch {
                emit(Result.withError(NetworkError(throwable = it)))
            }
    }
}
