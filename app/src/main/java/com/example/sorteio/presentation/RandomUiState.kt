package com.example.sorteio.presentation

data class RandomUiState(
    val number: String = "0",
    val min: String = "0",
    val max: String = "100",
    val isShaking: Boolean = false
)