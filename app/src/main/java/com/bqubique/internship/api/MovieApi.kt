package com.bqubique.internship.api

import com.bqubique.internship.model.Movie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("3/search/movie")
    suspend fun getMovies(
        @Query("api_key")
        apiKey: String = "fd2bf4839a4c7a7e72fc9d996073823f",
        @Query("query")
        query: String?
    ): Movie

    @GET("3/movie")
    suspend fun getMovieDetails(
        @Query("api_key")
        apiKey: String = "fd2bf4839a4c7a7e72fc9d996073823f",
        @Path("")
        movieId: String
    )
}