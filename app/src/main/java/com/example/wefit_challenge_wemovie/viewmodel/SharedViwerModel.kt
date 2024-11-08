package com.example.wefit_challenge_wemovie.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.wefit_challenge_wemovie.models.Movie

class SharedViewModel : ViewModel() {
    private val _cartItems = MutableLiveData<MutableList<Movie>>(mutableListOf())
    val cartItems: LiveData<MutableList<Movie>> get() = _cartItems

    private val _totalPrice = MutableLiveData<Double>(0.0)
    val totalPrice: LiveData<Double> = _cartItems.map { movies ->
        movies.sumOf { it.price * it.quantity }
    }


    private fun calculateTotalPrice() {
        _totalPrice.value = _cartItems.value?.sumOf { it.price * it.quantity } ?: 0.0
    }

    fun addToCart(movie: Movie) {
        _cartItems.value?.add(movie)
        _cartItems.value = _cartItems.value
        calculateTotalPrice() // Atualiza o totalPrice
    }

    fun removeFromCart(movie: Movie) {
        _cartItems.value?.remove(movie)
        _cartItems.value = _cartItems.value
        calculateTotalPrice() // Atualiza o totalPrice
    }
}
