package com.example.wefit_challenge_wemovie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        // Navegar para LoadingActivity após 2 segundos
        GlobalScope.launch {
            delay(2000)
            startActivity(Intent(this@MainActivity, LoadingActivity::class.java))
            finish()  // Finaliza a MainActivity para que não volte a ela ao pressionar 'Back'
        }
    }
}