package com.example.wefit_challenge_wemovie.data

import com.example.wefit_challenge_wemovie.models.RespostaMovie
import retrofit2.Response
import retrofit2.http.GET

interface MovieService {
    @GET("api/movies")
    suspend fun getMovies(): Response<RespostaMovie>

}