// CartFragment.kt
package com.example.wefit_challenge_wemovie.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wefit_challenge_wemovie.R
import com.example.wefit_challenge_wemovie.adapter.MovieAdapter
import com.example.wefit_challenge_wemovie.databinding.FragmentCartBinding
import com.example.wefit_challenge_wemovie.viewmodel.SharedViewModel

class CartFragment : Fragment(R.layout.fragment_cart) {

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentCartBinding
    private lateinit var adapter: MovieAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCartBinding.bind(view)

        adapter = MovieAdapter(sharedViewModel)
        binding.recyclerViewCart.adapter = adapter
        binding.recyclerViewCart.layoutManager = LinearLayoutManager(requireContext()) // Define o LayoutManager

        sharedViewModel.cartItems.observe(viewLifecycleOwner) { movies ->
            try {
                Log.d("CartFragment", "Lista de filmes no carrinho: $movies")
                adapter.submitList(movies)
                binding.recyclerViewCart.visibility = if (movies.isEmpty()) View.GONE else View.VISIBLE

                // Calcula o preço total
                val totalPrice = movies.sumOf { it.price * it.quantity }
                binding.totalPrice.text = "Total: R$ ${"%.2f".format(totalPrice)}"
            } catch (e: Exception) {
                Log.e("CartFragment", "Erro ao atualizar a lista de filmes", e)
                // Tratar o erro, ex: exibir uma mensagem para o usuário
            }
            binding.finalizeOrderButton.setOnClickListener {
                findNavController().navigate(R.id.action_cartFragment_to_orderConfirmationFragment)
            }
        }

    }
}