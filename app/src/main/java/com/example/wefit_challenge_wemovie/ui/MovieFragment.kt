package com.example.wefit_challenge_wemovie.ui


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.wefit_challenge_wemovie.R
import com.example.wefit_challenge_wemovie.data.MovieRepository
import com.example.wefit_challenge_wemovie.databinding.FragmentMovieBinding
import com.example.wefit_challenge_wemovie.viewmodel.HomeViewModel
import com.example.wefit_challenge_wemovie.viewmodel.HomeViewModelFactory

class MovieFragment : Fragment(R.layout.fragment_movie) {

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(MovieRepository()) // Use a factory aqui
    }

    private lateinit var binding: FragmentMovieBinding // Use o binding correto
    private lateinit var adapter: MovieAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)

        // Inicializa o adapter
        adapter = MovieAdapter { movie ->
            viewModel.addToCart(movie)
        }

        // Atribui o adapter ao RecyclerView
        binding.recyclerViewMoviesFragment.adapter = adapter

        // Observa a lista de filmes
        viewModel.movies.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}
