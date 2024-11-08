package com.example.wefit_challenge_wemovie.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.wefit_challenge_wemovie.R
import com.example.wefit_challenge_wemovie.databinding.FragmentCarrinhoVazioBinding

class CarrinhoVazioFragment : Fragment(R.layout.fragment_carrinho_vazio) {

    private lateinit var binding: FragmentCarrinhoVazioBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCarrinhoVazioBinding.bind(view)

        binding.btnBackToHome.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
    }
}