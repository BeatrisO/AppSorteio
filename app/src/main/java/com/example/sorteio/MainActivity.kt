package com.example.sorteio

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private lateinit var textViewNumeroGerado: TextView

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnSortear = findViewById<Button>(R.id.btn_sortear)
       textViewNumeroGerado = findViewById(R.id.textView_numeroGerado)


        btnSortear.setOnClickListener {
            sortear()
        }
    }

    fun sortear() {
         val numero = Random.nextInt(0, 101).toString()
        textViewNumeroGerado.text = numero

    }

}