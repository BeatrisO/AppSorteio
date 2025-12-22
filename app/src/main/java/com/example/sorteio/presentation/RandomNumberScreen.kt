package com.example.sorteio.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sorteio.viewmodel.RandomViewModel

@Composable
fun RandomNumberScreen(
    viewModel: RandomViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    val offsetX by animateFloatAsState(
        targetValue = if (state.isShaking) 1f else 0f,
        animationSpec = keyframes {
            durationMillis = 350
            (-8f).at(0)
            (8f).at(50)
            (-6f).at(100)
            (6f).at(150)
            (-4f).at(200)
            (4f).at(250)
            (0f).at(350)
        })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("Sorteio", fontSize = 32.sp)

        Spacer(Modifier.height(24.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {

            OutlinedTextField(
                value = state.min,
                onValueChange = viewModel::onMinChange,
                label = { Text("Número Inicial") },
                singleLine = true,
                modifier = Modifier.width(150.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = state.max,
                onValueChange = viewModel::onMaxChange,
                label = { Text("Número Final") },
                singleLine = true,
                modifier = Modifier.width(150.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

        Spacer(Modifier.height(40.dp))

        Text(
            text = state.number,
            fontSize = 64.sp,
            modifier = Modifier.offset(x = offsetX.dp)
        )

        Spacer(Modifier.height(40.dp))

        Button(
            onClick = viewModel::sort,
            modifier = Modifier
                .height(60.dp)
                .width(180.dp)
                .shadow(12.dp, RoundedCornerShape(20.dp)),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text("Sortear", fontSize = 20.sp)
        }
    }
}
