package com.example.wefit_challenge_wemovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wefit_challenge_wemovie.models.Movie
import com.example.wefit_challenge_wemovie.viewmodel.HomeViewModel
import com.example.wefit_challenge_wemovie.viewmodel.HomeViewModelFactory
import com.example.wefit_challenge_wemovie.data.MovieRepository
import com.example.wefit_challenge_wemovie.databinding.FragmentHomeBinding
import com.example.wefit_challenge_wemovie.ui.MovieAdapter


class HomeActivity : Fragment(R.layout.activity_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieAdapter: MovieAdapter
    private val homeViewModel: HomeViewModel by viewModels { HomeViewModelFactory(MovieRepository()) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Atribuindo o binding correto para o fragmento
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Retorne a raiz do layout do fragmento
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()

        // Chama a API para buscar os filmes
        homeViewModel.fetchMovies()
    }

    private fun setupRecyclerView() {
        movieAdapter = MovieAdapter { movie -> addToCart(movie) }
        binding.recyclerViewMovies.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        // Botão para recarregar
        binding.reloadButton.setOnClickListener {
            homeViewModel.fetchMovies() // Chama novamente a API ao clicar
        }
    }

    private fun setupObservers() {
        homeViewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
            if (movies.isNullOrEmpty()) {
                binding.recyclerViewMovies.visibility = View.GONE
                binding.emptyStateText.visibility = View.VISIBLE // Torna o texto "Nenhum filme encontrado" visível
                binding.reloadButton.visibility = View.VISIBLE // Torna o botão de recarregar visível
            } else {
                binding.recyclerViewMovies.visibility = View.VISIBLE
                binding.emptyStateText.visibility = View.GONE
                binding.reloadButton.visibility = View.GONE
                movieAdapter.submitList(movies)
            }
        })

        homeViewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            binding.reloadButton.visibility = if (isLoading) View.VISIBLE else View.GONE
        })
    }

    private fun addToCart(movie: Movie) {
        Toast.makeText(requireContext(), "${movie.title} adicionado ao Carrinho", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Liberando o binding quando a view é destruída
    }
}

