package com.example.wefit_challenge_wemovie.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.wefit_challenge_wemovie.R
import com.example.wefit_challenge_wemovie.databinding.FragmentOrderConfirmationBinding
import com.example.wefit_challenge_wemovie.fragment.HomeFragment

class OrderConfirmationFragment : Fragment(R.layout.fragment_order_confirmation) {

    private lateinit var binding: FragmentOrderConfirmationBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOrderConfirmationBinding.bind(view)

        binding.backToHomeButton.setOnClickListener {
            // Obtém o FragmentManager da Activity
            val fragmentManager = requireActivity().supportFragmentManager

            // Substitui o Fragment atual pelo HomeFragment
            fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, HomeFragment()) // Substitui no fragmentContainer
                .commit()
        }
    }
}