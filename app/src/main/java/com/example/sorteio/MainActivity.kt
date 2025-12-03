package com.example.sorteio

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sorteio.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnSortear.setOnClickListener {
            sortear()
        }
    }

    fun sortear() {
        binding.textViewNumeroGerado.animate()
            .translationX(20f)
            .setDuration(50)
            .withEndAction {
                binding.textViewNumeroGerado.animate()
                    .translationX(-20f)
                    .setDuration(50)
                    .withEndAction {
                        binding.textViewNumeroGerado.animate()
                            .translationX(0f)
                            .setDuration(50)
                    }
            }

        val numero = Random.nextInt(0, 101).toString()
        binding.textViewNumeroGerado.postDelayed({
            binding.textViewNumeroGerado.text = numero
        }, 150)
    }
}