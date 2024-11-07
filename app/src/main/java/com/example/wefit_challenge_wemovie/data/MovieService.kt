package com.example.wefit_challenge_wemovie.data

import com.example.wefit_challenge_wemovie.models.Movie
import retrofit2.http.GET
import retrofit2.Response

interface MovieService {
    @GET("movies")
    suspend fun getMovies(): Response<List<Movie>>
}