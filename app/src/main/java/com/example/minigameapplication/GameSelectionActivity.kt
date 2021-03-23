package com.example.minigameapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.minigameapplication.databinding.ActivityGameSelectionBinding
import com.example.minigameapplication.databinding.ActivityMainBinding

class GameSelectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameSelectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.hangmanBtn.setOnClickListener { startHangman(it)}
    }

    fun startHangman(view: View){
        val intent = Intent(this, HangmanActivity::class.java)
        startActivity(intent)

    }
}