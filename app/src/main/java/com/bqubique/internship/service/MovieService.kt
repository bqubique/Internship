package com.bqubique.internship.service

import android.util.Log
import com.bqubique.internship.api.MovieApi
import com.bqubique.internship.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieService {
    private val retrofit by lazy {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api : MovieApi by lazy {
        retrofit.create(MovieApi::class.java)
    }
}