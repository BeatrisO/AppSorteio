package com.example.sorteio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sorteio.presentation.RandomUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

class RandomViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RandomUiState())
    val uiState = _uiState.asStateFlow()

    fun sort() {

        val currentState = _uiState.value

        if (currentState.isShaking) return

        val min = currentState.min.toIntOrNull() ?: 0
        val max = currentState.max.toIntOrNull() ?: 100

        val realMin = min.coerceAtMost(max)
        val realMax = max.coerceAtLeast(min)

        viewModelScope.launch {

            _uiState.update { it.copy(isShaking = true) }

            repeat(12) {

                val randomNumber = Random.nextInt(realMin, realMax + 1)

                _uiState.update {
                    it.copy(number = randomNumber.toString())
                }

                delay(45)
            }

            _uiState.update { it.copy(isShaking = false) }
        }
    }

    fun onMinChange(value: String) {
        _uiState.update { it.copy(min = value) }
    }

    fun onMaxChange(value: String) {
        _uiState.update { it.copy(max = value) }
    }
}