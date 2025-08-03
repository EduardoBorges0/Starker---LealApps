package com.app.starker.presentation.navigation.routes.workout

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.app.starker.presentation.navigation.routes.workout.WorkoutRoutes.InsertWorkoutImage
import com.app.starker.presentation.navigation.routes.workout.WorkoutRoutes.ShowWorkout
import com.app.starker.presentation.workouts.insertWorkout.InsertWorkoutScreen
import com.app.starker.presentation.workouts.showWorkout.ShowWorkoutScreen
import com.app.starker.presentation.workouts.showWorkout.showWorkoutDetails.ShowWorkoutDetailsScreen
import com.google.firebase.Timestamp
import java.net.URLDecoder

fun NavGraphBuilder.NavWorkoutRoutes(navHostController: NavHostController) {
    composable(route = WorkoutRoutes.ShowWorkout.route) {
        ShowWorkoutScreen(navHostController)
    }
    composable(route = WorkoutRoutes.InsertWorkout.route, enterTransition = {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Up,
            tween(500)
        )
    }, exitTransition = {
        slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Down,
            tween(500)
        )
    }) {
        InsertWorkoutScreen(navHostController)
    }
    composable(route = WorkoutRoutes.ShowWorkoutDetails.route,
        arguments = listOf(
            navArgument("workoutId") { type = NavType.StringType },
        ),
        enterTransition = {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Up,
            tween(500)
        )
    }, exitTransition = {
        slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Down,
            tween(500)
        )
    }) { backStackEntry ->
        val workoutId =
            URLDecoder.decode(backStackEntry.arguments?.getString("workoutId") ?: "", "UTF-8")

        ShowWorkoutDetailsScreen(navHostController, workoutId)
    }

}