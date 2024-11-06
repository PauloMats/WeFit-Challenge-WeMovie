package com.example.wefit_challenge_wemovie.data

import com.example.wefit_challenge_wemovie.models.Movie
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://wefit-movies.vercel.app/api/movies")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(MovieService::class.java)

    suspend fun getMovies(): List<Movie> {
        return service.getMovies()
    }
}

