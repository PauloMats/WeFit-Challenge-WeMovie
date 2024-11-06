package com.example.wefit_challenge_wemovie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wefit_challenge_wemovie.models.Movie

class CartViewModel : ViewModel() {
    private val _cartItems = MutableLiveData<List<Movie>?>()
    val cartItems: MutableLiveData<List<Movie>?> get() = _cartItems

    private val _totalPrice = MutableLiveData<Double>()
    val totalPrice: LiveData<Double> get() = _totalPrice

    fun addItem(movie: Movie) {
        val updatedCart = _cartItems.value?.toMutableList() ?: mutableListOf()
        updatedCart.add(movie)
        _cartItems.value = updatedCart
        calculateTotal()
    }

    fun removeItem(movie: Movie) {
        val updatedCart = _cartItems.value?.toMutableList()
        updatedCart?.remove(movie)
        _cartItems.value = updatedCart
        calculateTotal()
    }

    private fun calculateTotal() {
        _totalPrice.value = _cartItems.value?.sumOf { it.price } ?: 0.0
    }
}
