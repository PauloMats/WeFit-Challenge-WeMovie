// SharedViewModel
package com.example.wefit_challenge_wemovie.viewmodel

import android.util.Log
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

    fun updateCartItem(movie: Movie) {
        try {
            // Encontra o filme na lista do carrinho
            val index = _cartItems.value?.indexOfFirst { it.id == movie.id } ?: -1
            if (index != -1) {
                // Atualiza a quantidade do filme
                _cartItems.value?.get(index)?.quantity = movie.quantity
                _cartItems.value = _cartItems.value // Notifica os observers
                calculateTotalPrice()

                Log.d("SharedViewModel", "Item atualizado no carrinho: $movie")
                Log.d("SharedViewModel", "Carrinho: ${_cartItems.value}")
            }
        } catch (e: Exception) {
            Log.e("SharedViewModel", "Erro ao atualizar item no carrinho", e)
            // Tratar o erro, ex: exibir uma mensagem para o usuário
        }
    }
    fun addToCart(movie: Movie) {
        try {
            _cartItems.value?.add(movie)
            _cartItems.value = _cartItems.value // Notifica os observers
            calculateTotalPrice()

            Log.d("SharedViewModel", "Filme adicionado: $movie")
            Log.d("SharedViewModel", "Carrinho: ${_cartItems.value}")
        } catch (e: Exception) {
            Log.e("SharedViewModel", "Erro ao adicionar filme ao carrinho", e)
            // Tratar o erro, ex: exibir uma mensagem para o usuário
        }
    }

    fun clearCart() {
        try {
            _cartItems.value?.clear()
            _cartItems.value = _cartItems.value // Notifica os observers
            calculateTotalPrice()

            Log.d("SharedViewModel", "Carrinho limpo")
        } catch (e: Exception) {
            Log.e("SharedViewModel", "Erro ao limpar o carrinho", e)
            // Tratar o erro, ex: exibir uma mensagem para o usuário
        }
    }

    fun removeFromCart(movie: Movie) {
        try {
            _cartItems.value?.remove(movie)
            _cartItems.value = _cartItems.value
            calculateTotalPrice()

            Log.d("SharedViewModel", "Filme removido: $movie")
            Log.d("SharedViewModel", "Carrinho: ${_cartItems.value}")
        } catch (e: Exception) {
            Log.e("SharedViewModel", "Erro ao remover filme do carrinho", e)
            // Tratar o erro, ex: exibir uma mensagem para o usuário
        }
    }
}