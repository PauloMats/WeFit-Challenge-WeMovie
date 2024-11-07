package com.example.wefit_challenge_wemovie.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wefit_challenge_wemovie.models.Movie

class SharedViewModel : ViewModel() {
    private val _cartItems = MutableLiveData<MutableList<Movie>>(mutableListOf())
    val cartItems: LiveData<MutableList<Movie>> get() = _cartItems

    fun addToCart(movie: Movie) {
        _cartItems.value?.add(movie)
        _cartItems.value = _cartItems.value // Isso notifica os observers sobre a mudança
    }

    fun removeFromCart(movie: Movie) {
        _cartItems.value?.remove(movie)
        _cartItems.value = _cartItems.value
    }
}