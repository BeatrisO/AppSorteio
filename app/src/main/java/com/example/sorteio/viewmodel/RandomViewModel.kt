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

    private val State = MutableStateFlow(RandomUiState())
    val uiState = State.asStateFlow()

    fun sort() {
        val min = State.value.min.toIntOrNull() ?: 0
        val max = State.value.max.toIntOrNull() ?: 100

        val realMin = min.coerceAtMost(max)
        val realMax = max.coerceAtLeast(min)

        viewModelScope.launch {
            State.value = State.value.copy(isShaking = true)

            repeat(12) {
                State.value = State.value.copy(
                    number = Random.Default.nextInt(realMin, realMax + 1).toString()
                )
                delay(45)
            }

            State.value = State.value.copy(isShaking = false)
        }
    }

    fun onMinChange(value: String) {
        State.value = State.value.copy(min = value)
    }

    fun onMaxChange(value: String) {
        State.value = State.value.copy(max = value)
    }
}