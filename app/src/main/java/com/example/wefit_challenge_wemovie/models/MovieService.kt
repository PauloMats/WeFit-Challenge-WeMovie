package com.example.wefit_challenge_wemovie.models

import retrofit2.http.GET

interface MovieService {
    @GET("api/movies")
    suspend fun getMovies(): List<Movie>
}