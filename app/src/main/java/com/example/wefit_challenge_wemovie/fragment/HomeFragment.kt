    package com.example.wefit_challenge_wemovie.fragment

    import android.os.Bundle
    import android.view.View
    import androidx.fragment.app.Fragment
    import androidx.lifecycle.Observer
    import com.example.wefit_challenge_wemovie.R
    import com.example.wefit_challenge_wemovie.ui.MovieAdapter
    import com.example.wefit_challenge_wemovie.databinding.FragmentHomeBinding
    import com.example.wefit_challenge_wemovie.viewmodel.HomeViewModel
    import com.example.wefit_challenge_wemovie.viewmodel.HomeViewModelFactory
    import com.example.wefit_challenge_wemovie.data.MovieRepository
    import androidx.fragment.app.viewModels
    import androidx.recyclerview.widget.LinearLayoutManager

    class HomeFragment : Fragment(R.layout.fragment_home) {

        private lateinit var movieAdapter: MovieAdapter
        private lateinit var binding: FragmentHomeBinding
        private val homeViewModel: HomeViewModel by viewModels { HomeViewModelFactory(MovieRepository()) }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            binding = FragmentHomeBinding.bind(view)

            setupObservers()
            binding.reloadButton.setOnClickListener {
                homeViewModel.fetchMovies() // Chama a API quando clicar
            }
            movieAdapter = MovieAdapter { movie ->
                homeViewModel.addToCart(movie)
            }
            binding.recyclerViewMovies.apply {
                adapter = movieAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }

        private fun setupObservers() {
            homeViewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
                if (movies.isNullOrEmpty()) {
                    binding.recyclerViewMovies.visibility = View.GONE
                    binding.emptyStateText.visibility = View.VISIBLE
                    binding.reloadButton.visibility = View.VISIBLE
                } else {
                    binding.recyclerViewMovies.visibility = View.VISIBLE
                    binding.emptyStateText.visibility = View.GONE
                    binding.reloadButton.visibility = View.GONE
                    movieAdapter.submitList(movies)
                }
            })
        }
    }