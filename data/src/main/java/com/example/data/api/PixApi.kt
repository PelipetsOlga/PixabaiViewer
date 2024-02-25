package com.example.data.api

import com.example.myapplication.data.models.PixResponse
import retrofit2.http.GET
import retrofit2.http.Query

 interface PixApi {
    @GET("/api/")
    suspend fun getSearchResults(
        @Query("key") key: String = "42532643-50f9c097dc69d5dc2b56fc669",
        @Query("q") keyword: String
    ): PixResponse
}
