package com.example.wefit_challenge_wemovie.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.wefit_challenge_wemovie.HomeActivity
import com.example.wefit_challenge_wemovie.R
import com.example.wefit_challenge_wemovie.databinding.FragmentCarrinhoVazioBinding
import com.example.wefit_challenge_wemovie.fragment.HomeFragment

class CarrinhoVazioFragment : Fragment(R.layout.fragment_carrinho_vazio) {

    private lateinit var binding: FragmentCarrinhoVazioBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCarrinhoVazioBinding.bind(view)

        binding.btnBackToHome.setOnClickListener {
            (activity as? HomeActivity)?.selectHomeMenuItem() //Seleciona o item Home no BottomNavigationView

            // Obtém o FragmentManager da Activity
            val fragmentManager = requireActivity().supportFragmentManager

            // Substitui o Fragment atual pelo HomeFragment
            fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, HomeFragment()) // Substitui no fragmentContainer
                .commit()
        }
    }
}