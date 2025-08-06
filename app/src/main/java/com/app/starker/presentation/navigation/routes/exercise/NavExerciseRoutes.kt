package com.app.starker.presentation.navigation.routes.exercise

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.app.starker.presentation.exercises.insertExercise.InsertExerciseViewModel
import com.app.starker.presentation.exercises.insertExercise.InsertExerciseScreen
import com.app.starker.presentation.exercises.updateExercise.UpdateExerciseScreen

fun NavGraphBuilder.NavExerciseRoutes(navHostController: NavHostController) {

    composable(
        route = ExerciseRoutes.InsertExercise.route,
        arguments = listOf(
            androidx.navigation.navArgument("workoutId") {
                type = androidx.navigation.NavType.StringType
            }
        ),
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Up,
                tween(500)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Down,
                tween(500)
            )
        }
    ) { backStackEntry ->
        val workoutId = backStackEntry.arguments?.getString("workoutId") ?: ""
        val viewModel: InsertExerciseViewModel = hiltViewModel()
        InsertExerciseScreen(workoutId, navHostController, viewModel)
    }

    composable(
        route = ExerciseRoutes.UpdateExercise.route,
        arguments = listOf(
            androidx.navigation.navArgument("workoutId") {
                type = androidx.navigation.NavType.StringType
            },
            androidx.navigation.navArgument("exerciseId") {
                type = androidx.navigation.NavType.StringType
            }
        ),
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Up,
                tween(500)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Down,
                tween(500)
            )
        }
    ) { backStackEntry ->
        val workoutId = backStackEntry.arguments?.getString("workoutId") ?: ""
        val exerciseId = backStackEntry.arguments?.getString("exerciseId") ?: ""
        UpdateExerciseScreen(
            workoutId = workoutId,
            exerciseId = exerciseId,
            navHostController = navHostController
        )
    }
}