package com.example.wefit_challenge_wemovie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wefit_challenge_wemovie.databinding.ActivityHomeBinding
import com.example.wefit_challenge_wemovie.fragment.HomeFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Adicionar o HomeFragment ao FrameLayout
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer,
        HomeFragment()
        )
        fragmentTransaction.commit()

    }
}