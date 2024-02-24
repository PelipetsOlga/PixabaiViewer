package com.example.myapplication.di

import com.example.myapplication.data.api.PixApi
import com.example.myapplication.data.api.PixApiHelper
import com.example.myapplication.data.api.PixApiHelperImpl
import com.example.myapplication.data.repo.PixRepositoryImpl
import com.example.myapplication.domain.repo.PixRepository
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
class AppModule

@InstallIn(SingletonComponent::class)
@Module
internal class ApiModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
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

    @Provides
    @Singleton
    fun provideApiHelper(api: PixApi): PixApiHelper {
        return PixApiHelperImpl(api)
    }
}

@InstallIn(SingletonComponent::class)
@Module
internal interface RepoModule {
    @Binds
    @Singleton
    fun bindRepository(repo: PixRepositoryImpl): PixRepository
}
