package com.example.sorteio.presentation

data class RandomUiState(
    val min: String = "",
    val max: String = "",
    val number: String = "0",
    val isShaking: Boolean = false
)