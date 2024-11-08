package com.example.wefit_challenge_wemovie.ui


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.wefit_challenge_wemovie.R
import com.example.wefit_challenge_wemovie.data.MovieRepository
import com.example.wefit_challenge_wemovie.databinding.FragmentMovieBinding
import com.example.wefit_challenge_wemovie.viewmodel.HomeViewModel
import com.example.wefit_challenge_wemovie.viewmodel.HomeViewModelFactory
import com.example.wefit_challenge_wemovie.viewmodel.SharedViewModel


class MovieFragment : Fragment(R.layout.fragment_movie) {

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(MovieRepository())
    }

    private lateinit var binding: FragmentMovieBinding
    private lateinit var adapter: MovieAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)

        val sharedViewModel: SharedViewModel by activityViewModels()
        adapter = MovieAdapter(sharedViewModel)

        // Atribui o adapter ao RecyclerView
        binding.recyclerViewMoviesFragment.adapter = adapter

        // Observa a lista de filmes
        viewModel.movies.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}
