package com.example.wefit_challenge_wemovie.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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
        binding.recyclerViewCart.layoutManager = LinearLayoutManager(requireContext())

        sharedViewModel.cartItems.observe(viewLifecycleOwner) { movies ->
            try {
                Log.d("CartFragment", "Lista de filmes no carrinho: $movies")

                if (movies.isEmpty()) {
                    // Exibe o CarrinhoVazioFragment
                    childFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerCart, CarrinhoVazioFragment())
                        .commit()

                    // Oculta os elementos do carrinho
                    binding.recyclerViewCart.visibility = View.GONE
                    binding.totalLabel.visibility = View.GONE
                    binding.totalPrice.visibility = View.GONE
                    binding.finalizeOrderButton.visibility = View.GONE
                } else {
                    // Exibe o RecyclerView com os filmes
                    childFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerCart, Fragment()) // Substitui por um Fragment vazio
                        .commit()

                    // Exibe os elementos do carrinho
                    binding.recyclerViewCart.visibility = View.VISIBLE
                    binding.totalLabel.visibility = View.VISIBLE
                    binding.totalPrice.visibility = View.VISIBLE
                    binding.finalizeOrderButton.visibility = View.VISIBLE

                    adapter.submitList(movies)

                    // Define o OnClickListener do botão "Finalizar Pedido"
                    binding.finalizeOrderButton.setOnClickListener {
                        sharedViewModel.clearCart() // Limpa o carrinho

                        // Carrega o OrderConfirmationFragment no fragmentContainerCart
                        childFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerCart, OrderConfirmationFragment())
                            .commit()
                    }
                }

                // Calcula o preço total
                val totalPrice = movies.sumOf { it.price * it.quantity }
                binding.totalPrice.text = "Total: R$ ${"%.2f".format(totalPrice)}"
            } catch (e: Exception) {
                Log.e("CartFragment", "Erro ao atualizar a lista de filmes", e)
                // Tratar o erro, ex: exibir uma mensagem para o usuário
            }
        }
    }
}