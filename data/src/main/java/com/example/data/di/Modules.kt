package com.example.data.di

import com.example.data.api.PixApi
import com.example.data.repo.PixRepositoryImpl
import com.example.domain.repo.PixRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(includes = [RepoModule::class, ApiModule::class])
class DataModule

@InstallIn(SingletonComponent::class)
@Module
internal class ApiModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl("https://pixabay.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): PixApi {
        return retrofit.create(PixApi::class.java)
    }
}

@InstallIn(SingletonComponent::class)
@Module
internal interface RepoModule {
    @Binds
    @Singleton
    fun bindRepository(repo: PixRepositoryImpl): PixRepository
}
