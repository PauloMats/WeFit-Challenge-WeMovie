package com.example.wefit_challenge_wemovie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.wefit_challenge_wemovie.data.MovieRepository
import com.example.wefit_challenge_wemovie.databinding.FragmentHomeBinding
import com.example.wefit_challenge_wemovie.viewmodels.HomeViewModel
import com.example.wefit_challenge_wemovie.viewmodels.HomeViewModelFactory

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, HomeViewModelFactory(MovieRepository())).get(HomeViewModel::class.java)

        adapter = MovieAdapter { movie -> viewModel.addToCart(movie) }
        binding.recyclerView.adapter = adapter

        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            adapter.submitList(movies)
        }

        return binding.root
    }
}
