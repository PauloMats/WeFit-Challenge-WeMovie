package com.example.wefit_challenge_wemovie

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class LoadingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        // Navegar para HomeActivity após 2 segundos
        GlobalScope.launch {
            delay(2000)
            startActivity(Intent(this@LoadingActivity, HomeActivity::class.java))
            finish()  // Finaliza a LoadingActivity para que não volte a ela ao pressionar 'Back'
        }
    }
}