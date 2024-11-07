package com.example.wefit_challenge_wemovie.data

import android.util.Log
import com.example.wefit_challenge_wemovie.models.Movie
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://wefit-movies.vercel.app/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(MovieService::class.java)

    suspend fun getMovies(): List<Movie>? {
        val response = service.getMovies()
        if (response.isSuccessful) {
            val movies = response.body()
            Log.d("MovieRepository", "Movies retrieved: $movies")
            return movies
        } else {
            Log.e("MovieRepository", "API call failed: ${response.errorBody()?.string()}")
            return null
        }
    }
}

