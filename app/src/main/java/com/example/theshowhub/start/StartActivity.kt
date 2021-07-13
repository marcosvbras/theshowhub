package com.example.theshowhub.start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.theshowhub.home.presentation.HomeActivity
import com.example.theshowhub.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setupComponents()
    }

    private fun setupComponents() {
        viewBinding.startButton.setOnClickListener { onStartButtonClicked() }
    }

    private fun onStartButtonClicked() {
        val homeIntent = Intent(this, HomeActivity::class.java)
        startActivity(homeIntent)
    }

}