package com.example.sorteio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sorteio.presentation.RandomUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class RandomViewModel : ViewModel() {

    private val state = MutableStateFlow(RandomUiState())
    val uiState = state.asStateFlow()

    fun sort() {
        val min = state.value.min.toIntOrNull() ?: 0
        val max = state.value.max.toIntOrNull() ?: 100

        val realMin = min.coerceAtMost(max)
        val realMax = max.coerceAtLeast(min)

        viewModelScope.launch {
            state.value = state.value.copy(isShaking = true)

            repeat(12) {
                state.value = state.value.copy(
                    number = Random.Default.nextInt(realMin, realMax + 1).toString()
                )
                delay(45)
            }

            state.value = state.value.copy(isShaking = false)
        }
    }

    fun onMinChange(value: String) {
        state.value = state.value.copy(min = value)
    }

    fun onMaxChange(value: String) {
        state.value = state.value.copy(max = value)
    }
}