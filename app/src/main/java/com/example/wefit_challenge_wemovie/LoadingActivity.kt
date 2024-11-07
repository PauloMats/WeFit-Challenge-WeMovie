package com.example.wefit_challenge_wemovie

import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import android.content.Intent
import android.os.Bundle

class LoadingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_loading)


        // Navegar para a HomeActivity após 2 segundos
        GlobalScope.launch {
            delay(2000)
            startActivity(Intent(this@LoadingActivity, HomeActivity::class.java))
            finish()
        }
    }
}