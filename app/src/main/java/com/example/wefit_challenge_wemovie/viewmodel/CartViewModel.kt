package com.example.wefit_challenge_wemovie.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wefit_challenge_wemovie.models.Movie

class CartViewModel : ViewModel() {
    private val _cartItems = MutableLiveData<MutableList<Movie>>(mutableListOf())
    val cartItems: MutableLiveData<MutableList<Movie>> get() = _cartItems

    private val _totalPrice = MutableLiveData<Double>()
    val totalPrice: LiveData<Double> get() = _totalPrice

    init {
        calculateTotalPrice()
    }

    // Adiciona um filme ao carrinho
    fun addToCart(movie: Movie) {
        _cartItems.value?.add(movie)
        _cartItems.value = _cartItems.value

        Log.d("SharedViewModel", "Filme adicionado: $movie")
        Log.d("SharedViewModel", "Carrinho: ${_cartItems.value}")
    }

    // Remove um filme do carrinho
    fun removeFromCart(movie: Movie) {
        _cartItems.value?.remove(movie)
        calculateTotalPrice()
    }

    // Calcula o valor total do carrinho
    private fun calculateTotalPrice() {
        _totalPrice.value = _cartItems.value?.sumOf { it.price * it.quantity } ?: 0.0
    }
}
