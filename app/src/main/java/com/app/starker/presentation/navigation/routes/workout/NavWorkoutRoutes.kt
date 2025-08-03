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
import com.google.firebase.Timestamp
import java.net.URLDecoder

fun NavGraphBuilder.NavWorkoutRoutes(navHostController: NavHostController) {
    composable(route = WorkoutRoutes.ShowWorkout.route) {
        ShowWorkoutScreen(navHostController)
    }
    composable(route = WorkoutRoutes.InsertWorkout.route, enterTransition = {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Up,
            tween(1000)
        )
    }, exitTransition = {
        slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Down,
            tween(1000)
        )
    }) {
        InsertWorkoutScreen(navHostController)
    }
//    composable(
//        route = InsertWorkoutImage.route,
//        arguments = listOf(
//            navArgument("workoutName") { type = NavType.StringType },
//            navArgument("workoutDescription") { type = NavType.StringType },
//            navArgument("date") { type = NavType.StringType } // em milissegundos
//        ),
//        enterTransition = {
//            slideIntoContainer(
//                AnimatedContentTransitionScope.SlideDirection.Up,
//                tween(1000)
//            )
//        }, exitTransition = {
//            slideOutOfContainer(
//                AnimatedContentTransitionScope.SlideDirection.Down,
//                tween(1000)
//            )
//        }
//    ) { backStackEntry ->
//        val workoutName = URLDecoder.decode(backStackEntry.arguments?.getString("workoutName") ?: "", "UTF-8")
//        val workoutDescription = URLDecoder.decode(backStackEntry.arguments?.getString("workoutDescription") ?: "", "UTF-8")
//        val date = URLDecoder.decode(backStackEntry.arguments?.getString("date") ?: "", "UTF-8")
//        InsertWorkoutImageScreen(
//            workoutName = workoutName,
//            workoutDescription = workoutDescription,
//            date = date,
//            navHostController = navHostController
//        )
//    }

}