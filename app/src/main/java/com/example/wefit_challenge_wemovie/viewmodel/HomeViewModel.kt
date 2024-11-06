package com.example.wefit_challenge_wemovie.viewmodel

import androidx.lifecycle.*
import com.example.wefit_challenge_wemovie.data.MovieRepository
import com.example.wefit_challenge_wemovie.models.Movie
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movies = MutableLiveData<List<Movie>>()
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
                val movieList = repository.getMovies()  // Chama o `getMovies` do `MovieRepository`
                _movies.value = movieList
            } catch (e: Exception) {
                _movies.value = emptyList()  // ou tratamento de erro
            } finally {
                _loading.value = false
            }
        }
    }

    fun addToCart(movie: Movie) {
        // Lógica para adicionar ao carrinho
    }
}
