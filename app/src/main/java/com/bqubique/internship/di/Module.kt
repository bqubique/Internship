package com.bqubique.internship.di

import com.bqubique.internship.api.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    @Named("movieApiBaseUrl")
    fun provideMovieApiBaseUrl() = "https://api.themoviedb.org"

    @Singleton
    @Provides
    @Named("movieImageBaseUrl")
    fun provideMovieImageBaseUrl() = "https://image.tmdb.org/t/p/w500"

    @Singleton
    @Provides
    fun provideMovieApi(
        @Named("movieApiBaseUrl")
        baseUrl: String?
    ): MovieApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }
}