package com.example.wefit_challenge_wemovie.data

import android.util.Log
import com.example.wefit_challenge_wemovie.models.Movie
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://wefit-movies.vercel.app/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(MovieService::class.java)

    suspend fun getMovies(): List<Movie>? {
        return try {
            Log.d("MovieRepository", "Chamando getMovies()")

            val response = service.getMovies()

            Log.d("MovieRepository", "Resposta da API: $response")

            if (response.isSuccessful) {
                val movieResponse = response.body()
                Log.d("MovieRepository", "Movies retrieved: ${movieResponse?.products}")
                movieResponse?.products // Retorna a lista de filmes
            } else {
                Log.e("MovieRepository", "API call failed: ${response.code()} - ${response.errorBody()?.string()}")
                null
            }
        } catch (e: Exception) {
            Log.e("MovieRepository", "Error fetching movies", e)
            null

        }

    }
}