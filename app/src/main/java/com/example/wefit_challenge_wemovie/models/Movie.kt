package com.example.wefit_challenge_wemovie.models

data class RespostaMovie(
    val products: List<Movie>
)

data class Movie(
    val id: Int,
    val title: String,
    val price: Double,
    val image: String,
    var quantity: Int = 0
)