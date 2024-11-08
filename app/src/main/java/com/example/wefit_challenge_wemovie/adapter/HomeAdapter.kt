// HomeAdapter.kt
package com.example.wefit_challenge_wemovie.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wefit_challenge_wemovie.databinding.ItemMovieCardBinding
import com.example.wefit_challenge_wemovie.models.Movie
import com.example.wefit_challenge_wemovie.viewmodel.SharedViewModel

class HomeAdapter(private val sharedViewModel: SharedViewModel) :
    RecyclerView.Adapter<HomeAdapter.MovieViewHolder>() {

    private var movieList: List<Movie> = emptyList()

    class MovieViewHolder(val binding: ItemMovieCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.binding.movie = movie

        Glide.with(holder.itemView.context)
            .load(movie.image)
            .into(holder.binding.movieImage)

        // Listener combinado para o botão
        holder.binding.addButton.setOnClickListener {
            holder.binding.clickHandler?.onCartButtonClick(movie) // Chama a função do clickHandler
            sharedViewModel.addToCart(movie) // Adiciona o filme ao carrinho
            Log.d("HomeAdapter", "Adicionando filme ao carrinho: $movie")
        }

        Log.d("HomeAdapter", "onBindViewHolder chamado para posição $position")
        Log.d("HomeAdapter", "Filme: ${movieList[position]}")
    }

    override fun getItemCount(): Int = movieList.size

    fun submitList(filmes: List<Movie>) {
        this.movieList = filmes
        notifyDataSetChanged()
    }
}