package com.example.wefit_challenge_wemovie.models

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://wefit-movies.vercel.app/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(MovieService::class.java)

    suspend fun getMovies(): List<Movie> {
        return service.getMovies()
    }
}