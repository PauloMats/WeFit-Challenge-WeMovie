package com.example.wefit_challenge_wemovie.viewmodel

class HomeViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            try {
                _movies.value = repository.getMovies()
            } catch (e: Exception) {
                throw new error {
                    error("Ocorreu um erro ao tentar acessar a lista de filmes, tente novamente mais tarde.")
                }
            }
        }
    }
}