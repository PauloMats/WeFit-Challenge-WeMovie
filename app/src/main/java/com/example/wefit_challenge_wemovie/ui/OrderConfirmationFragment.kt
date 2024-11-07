package com.example.wefit_challenge_wemovie.ui

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.wefit_challenge_wemovie.R
import com.example.wefit_challenge_wemovie.databinding.FragmentOrderConfirmationBinding


class OrderConfirmationFragment : Fragment(R.layout.fragment_order_confirmation) {

    private lateinit var binding: FragmentOrderConfirmationBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOrderConfirmationBinding.bind(view)

        binding.backToHomeButton.setOnClickListener {
            findNavController().navigate(R.id.action_cartFragment_to_orderConfirmationFragment)
        }
    }
}
