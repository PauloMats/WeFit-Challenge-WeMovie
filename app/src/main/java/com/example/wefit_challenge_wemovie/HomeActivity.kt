package com.example.wefit_challenge_wemovie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wefit_challenge_wemovie.databinding.ActivityHomeBinding
import com.example.wefit_challenge_wemovie.fragment.HomeFragment
import com.example.wefit_challenge_wemovie.ui.CartFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, HomeFragment())
            .commit()

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar os listeners da barra de navegação
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, HomeFragment())
                        .commit()
                    true
                }
                R.id.navigation_cart -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, CartFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}