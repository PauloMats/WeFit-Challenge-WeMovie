package com.example.wefit_challenge_wemovie.viewmodel

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
        calculateTotalPrice()
    }

    // Remove um filme do carrinho
    fun removeFromCart(movie: Movie) {
        _cartItems.value?.remove(movie)
        calculateTotalPrice()
    }

    // Calcula o valor total do carrinho
    private fun calculateTotalPrice() {
        _totalPrice.value = _cartItems.value?.sumOf { it.price } ?: 0.0
    }
}
