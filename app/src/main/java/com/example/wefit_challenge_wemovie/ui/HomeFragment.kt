package com.example.wefit_challenge_wemovie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.wefit_challenge_wemovie.data.MovieRepository
import com.example.wefit_challenge_wemovie.databinding.FragmentHomeBinding
import com.example.wefit_challenge_wemovie.viewmodel.HomeViewModel
import com.example.wefit_challenge_wemovie.viewmodel.HomeViewModelFactory


class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: MovieAdapter
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, HomeViewModelFactory(MovieRepository())).get(HomeViewModel::class.java)

        // Configurar o adapter e o recyclerView
        adapter = MovieAdapter(viewModel::addToCart)
        binding.recyclerView.adapter = adapter

        // Observa o estado de carregamento
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.loadingSpinner.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        // Observa a lista de filmes e o estado vazio
        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            if (movies.isNullOrEmpty()) {
                binding.emptyStateText.visibility = View.VISIBLE
                binding.reloadButton.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            } else {
                binding.emptyStateText.visibility = View.GONE
                binding.reloadButton.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
                adapter.submitList(movies)
            }
        }

        // Configura o botão de recarregar
        binding.reloadButton.setOnClickListener {
            viewModel.fetchMovies()
        }

        return binding.root
    }
}
