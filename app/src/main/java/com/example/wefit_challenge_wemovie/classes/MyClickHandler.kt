package com.example.wefit_challenge_wemovie.classes

import android.util.Log
import androidx.databinding.ObservableBoolean
import com.example.wefit_challenge_wemovie.models.Movie
import com.example.wefit_challenge_wemovie.viewmodel.SharedViewModel

class MyClickHandler(private val sharedViewModel: SharedViewModel) {

    val isButtonPressed = ObservableBoolean(false)

    fun onCartButtonClick(movie: Movie) {
        isButtonPressed.set(!isButtonPressed.get()) // Inverte o estado do botão
        sharedViewModel.addToCart(movie)
        Log.d("MyClickHandler", "Filme adicionado ao carrinho: $movie")
    }
}