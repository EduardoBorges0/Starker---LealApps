package com.app.starker.presentation.navigation.routes.workout

sealed class WorkoutRoutes(
    val route: String
) {
    object MainWorkout : WorkoutRoutes("mainWorkout")
}