// MovieAdapter.kt
package com.example.wefit_challenge_wemovie.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wefit_challenge_wemovie.databinding.ItemMovieCartBinding // Use o Binding do item do carrinho
import com.example.wefit_challenge_wemovie.models.Movie
import com.example.wefit_challenge_wemovie.viewmodel.SharedViewModel
import java.text.SimpleDateFormat
import java.util.Locale

class MovieAdapter(private val sharedViewModel: SharedViewModel) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movieList: List<Movie> = emptyList()

    class MovieViewHolder(val binding: ItemMovieCartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.binding.movie = movie // Vincula o objeto Movie ao Binding

        // Formata a data
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val dateAdded = dateFormat.format(Date()) // Substitue Date() pela data real de adição
        holder.binding.dateAdded.text = "Adicionado em $dateAdded"

        // Lógica dos botões de aumentar/diminuir quantidade
        holder.binding.decreaseButton.setOnClickListener {
            if (movie.quantity > 1) {
                movie.quantity--
                sharedViewModel.updateCartItem(movie) // Atualiza o item no carrinho
            }
        }
        holder.binding.increaseButton.setOnClickListener {
            movie.quantity++
            sharedViewModel.updateCartItem(movie) // Atualiza o item no carrinho
        }

        // Lógica do botão de remover
        holder.binding.removeButton.setOnClickListener {
            sharedViewModel.removeFromCart(movie)
        }

        Log.d("MovieAdapter", "onBindViewHolder chamado para posição $position")
        Log.d("MovieAdapter", "Filme: ${movieList[position]}")
    }

    override fun getItemCount(): Int = movieList.size

    fun submitList(filmes: List<Movie>) {
        this.movieList = filmes
        notifyDataSetChanged()
    }
}