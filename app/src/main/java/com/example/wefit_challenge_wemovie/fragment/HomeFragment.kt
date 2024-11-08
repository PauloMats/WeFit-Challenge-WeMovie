package com.example.wefit_challenge_wemovie.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.wefit_challenge_wemovie.R
import com.example.wefit_challenge_wemovie.adapter.MovieAdapter
import com.example.wefit_challenge_wemovie.databinding.FragmentHomeBinding
import com.example.wefit_challenge_wemovie.viewmodel.HomeViewModel
import com.example.wefit_challenge_wemovie.viewmodel.HomeViewModelFactory
import com.example.wefit_challenge_wemovie.data.MovieRepository
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wefit_challenge_wemovie.adapter.HomeAdapter
import com.example.wefit_challenge_wemovie.classes.MyClickHandler
import com.example.wefit_challenge_wemovie.viewmodel.SharedViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var movieAdapter: HomeAdapter
    private lateinit var binding: FragmentHomeBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by viewModels { HomeViewModelFactory(MovieRepository()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        setupObservers()

       binding.clickHandler = MyClickHandler(sharedViewModel)

        binding.reloadButton.setOnClickListener {
            binding.loadingSpinner.visibility = View.VISIBLE
            homeViewModel.fetchMovies()
            binding.emptyStateHomeText.visibility = View.GONE
            binding.reloadButton.visibility = View.GONE
        }
        movieAdapter = HomeAdapter(sharedViewModel)

        binding.recyclerViewMovies.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerViewMovies.layoutManager = LinearLayoutManager(requireContext())

        }
    }

    private fun setupObservers() {
        homeViewModel.movies.observe(viewLifecycleOwner) {
            movieAdapter.submitList(it ?: emptyList())

            val hasMovies = it?.isNotEmpty() == true
            binding.loadingSpinner.visibility = View.GONE
            binding.recyclerViewMovies.visibility = if (hasMovies) View.VISIBLE else View.GONE
            binding.emptyStateHomeText.visibility = if (hasMovies) View.GONE else View.VISIBLE
            binding.reloadButton.visibility = if (hasMovies) View.GONE else View.VISIBLE
        }
    }
}