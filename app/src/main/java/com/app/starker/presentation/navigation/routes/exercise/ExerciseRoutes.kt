package com.app.starker.presentation.navigation.routes.exercise

sealed class ExerciseRoutes(
    val route: String
) {
    object InsertExercise : ExerciseRoutes("insert_exercise/{workoutId}") {
        fun createRoute(workoutId: String) = "insert_exercise/$workoutId"
    }

    object UpdateExercise : ExerciseRoutes("update_exercise/{workoutId}/{exerciseId}") {
        fun createRoute(workoutId: String, exerciseId: String) =
            "update_exercise/$workoutId/$exerciseId"
    }
}