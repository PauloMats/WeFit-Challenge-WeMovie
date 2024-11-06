package com.example.wefit_challenge_wemovie

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wefit_challenge_wemovie.databinding.ActivityHomeBinding
import com.example.wefit_challenge_wemovie.models.Movie
import com.example.wefit_challenge_wemovie.viewmodel.HomeViewModel
import com.example.wefit_challenge_wemovie.viewmodel.HomeViewModelFactory
import com.example.wefit_challenge_wemovie.data.MovieRepository
import com.example.wefit_challenge_wemovie.ui.MovieAdapter


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var movieAdapter: MovieAdapter

    // Instância de MovieRepository
    private val repository by lazy { MovieRepository() }

    // Instância de HomeViewModel usando o Factory
    private val homeViewModel: HomeViewModel by viewModels { HomeViewModelFactory(repository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeViewModel()

        homeViewModel.fetchMovies() // Inicia a requisição dos filmes
    }

    private fun setupRecyclerView() {
        movieAdapter = MovieAdapter { movie -> addToCart(movie) }
        binding.recyclerView.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(this@HomeActivity)
        }
    }

    private fun observeViewModel() {
        // Observa mudanças na lista de filmes
        homeViewModel.movies.observe(this, Observer { movies ->
            if (movies.isNullOrEmpty()) {
                binding.recyclerView.visibility = View.GONE
                Toast.makeText(this, "No movies found", Toast.LENGTH_SHORT).show()
            } else {
                binding.recyclerView.visibility = View.VISIBLE
                movieAdapter.submitList(movies)
            }
        })

        // Observa o estado de carregamento
        homeViewModel.loading.observe(this, Observer { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })
    }

    private fun addToCart(movie: Movie) {
        Toast.makeText(this, "${movie.title} added to cart", Toast.LENGTH_SHORT).show()
        // Implementar a lógica de adicionar ao carrinho
    }
}