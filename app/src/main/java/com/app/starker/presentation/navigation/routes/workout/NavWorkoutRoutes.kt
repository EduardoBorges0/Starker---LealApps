package com.app.starker.presentation.navigation.routes.workout

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.starker.presentation.workouts.MainWorkoutScreen

fun NavGraphBuilder.NavWorkoutRoutes() {
    composable(route = WorkoutRoutes.MainWorkout.route) {
        MainWorkoutScreen()
    }
}