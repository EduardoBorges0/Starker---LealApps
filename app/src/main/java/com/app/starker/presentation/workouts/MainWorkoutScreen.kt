package com.app.starker.presentation.workouts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MainWorkoutScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("WORKOUT SCREEN", modifier = Modifier.align(Alignment.Center))
    }
}