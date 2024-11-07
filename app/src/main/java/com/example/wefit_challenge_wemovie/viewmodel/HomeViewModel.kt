package com.example.wefit_challenge_wemovie.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.wefit_challenge_wemovie.data.MovieRepository
import com.example.wefit_challenge_wemovie.models.Movie
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.*
import kotlin.collections.MutableList
import kotlin.collections.emptyList
import kotlin.collections.find


private val Any?.products: List<Movie>?
    get() {
        return when (this) {
            is List<*> -> this.filterIsInstance<Movie>()
            else -> null
        }
    }

class HomeViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movies = MutableLiveData<List<Movie>>()
    private val _cartItems = MutableLiveData<MutableList<Movie>>(mutableListOf())
    val cartItems: LiveData<MutableList<Movie>> get() = _cartItems

    val movies: LiveData<List<Movie>> get() = _movies

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    init {
        fetchMovies()  // Chamando fetchMovies na inicialização
    }

    fun fetchMovies() {
        _loading.value = true
        viewModelScope.launch {
            try {
                val movieList = repository.getMovies()
                _movies.value = movieList?.products ?: emptyList()
            } catch (e: Exception) {
                _movies.value = emptyList()
                Log.e("HomeViewModel", "Erro ao buscar filmes", e)
            } finally {
                _loading.value = false
            }
        }
    }

    fun addToCart(movie: Movie) {
        // 1. Verifica se o filme já está no carrinho
        val existingMovie = _cartItems.value?.find { it.id == movie.id }

        if (existingMovie != null) {
            // Se o filme já existe, aumenta a quantidade
            existingMovie.quantity++
        } else {
            // Se o filme não existe, adiciona ao carrinho com quantidade 1
            movie.quantity = 1
            _cartItems.value?.add(movie)
        }

        // 2. Atualiza o LiveData para notificar os observers
        _cartItems.value = _cartItems.value

        Log.d("HomeViewModel", "Filmes atualizados: ${_movies.value}")
    }

}

private operator fun Any.inc(): Any {
    return when (this) {
        is Int -> this + 1
        is Long -> this + 1
        else -> throw UnsupportedOperationException("Unsupported type")
    }
}
