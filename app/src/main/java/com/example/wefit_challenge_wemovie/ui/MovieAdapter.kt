package com.example.wefit_challenge_wemovie.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wefit_challenge_wemovie.databinding.ItemMovieCardBinding
import com.example.wefit_challenge_wemovie.models.Movie
import com.example.wefit_challenge_wemovie.viewmodel.SharedViewModel

class MovieAdapter(private val sharedViewModel: SharedViewModel) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movieList: List<Movie> = emptyList() // Use List<Filme>

    class MovieViewHolder(val binding: ItemMovieCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val filme = movieList[position] // Use a variável filme
        holder.binding.movieTitle.text = filme.title // Acesse a propriedade title de Filme

        Glide.with(holder.itemView.context)
            .load(filme.image) // Acesse a propriedade image de Filme
            .into(holder.binding.movieImage)

        holder.binding.addButton.setOnClickListener {
            sharedViewModel.addToCart(filme)  // Passa o objeto Filme para o listener
            Log.d("MovieAdapter", "Adicionando filme ao carrinho: $filme")
        }
        Log.d("MovieAdapter", "onBindViewHolder chamado para posição $position")
        Log.d("MovieAdapter", "Filme: ${movieList[position]}")
    }

    override fun getItemCount(): Int = movieList.size

    fun submitList(filmes: List<Movie>) { // Use filmes: List<Filme>
        this.movieList = filmes
        notifyDataSetChanged()
    }
}