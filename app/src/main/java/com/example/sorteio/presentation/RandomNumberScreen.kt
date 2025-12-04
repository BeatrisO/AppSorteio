package com.example.sorteio.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun RandomNumberScreen() {
    var number by remember { mutableStateOf("0") }

    var startShake by remember { mutableStateOf(false) }

    var triggerSort by remember { mutableStateOf(false) }

    val offsetX by animateFloatAsState(
        targetValue = if (startShake) 20f else 0f,
        animationSpec = tween(durationMillis = 50),
        finishedListener = {
            if (startShake) startShake = false
        }
    )

    LaunchedEffect(triggerSort) {
        if (triggerSort) {
            delay(150)
            number = Random.nextInt(0, 101).toString()
            triggerSort = false
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = number,
            modifier = Modifier.offset(x = offsetX.dp),
            fontSize = 48.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = {
            startShake = true
            triggerSort = true
        }) {
            Text("Draw")
        }
    }
}
