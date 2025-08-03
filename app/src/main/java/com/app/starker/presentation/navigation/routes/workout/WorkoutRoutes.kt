package com.app.starker.presentation.navigation.routes.workout

import com.google.firebase.Timestamp
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class WorkoutRoutes(
    val route: String
) {
    object ShowWorkout : WorkoutRoutes("show_workout")
    object InsertWorkout : WorkoutRoutes("insert_workout")
    object ShowWorkoutDetails : WorkoutRoutes("show_workout_details/{workoutId}"){
        fun createRoute(workoutId: String): String{
            val encodedWorkoutId = URLEncoder.encode(workoutId, StandardCharsets.UTF_8.toString())
            return "show_workout_details/$encodedWorkoutId"
        }
    }

    object InsertWorkoutImage : WorkoutRoutes("insert_workout_image/{workoutName}/{workoutDescription}/{date}") {
        fun createRoute(workoutName: String, workoutDescription: String, date: String): String {
            val encodedName = URLEncoder.encode(workoutName, StandardCharsets.UTF_8.toString())
            val encodedDescription = URLEncoder.encode(workoutDescription, StandardCharsets.UTF_8.toString())
            val encodedDate = URLEncoder.encode(date, StandardCharsets.UTF_8.toString())
            return "insert_workout_image/$encodedName/$encodedDescription/$encodedDate"
        }
    }


}