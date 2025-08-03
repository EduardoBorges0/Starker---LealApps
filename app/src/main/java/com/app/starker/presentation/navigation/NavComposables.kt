package com.app.starker.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import com.app.starker.presentation.navigation.routes.auth.AuthRoutes
import com.app.starker.presentation.navigation.routes.auth.NavAuthRoutes
import com.app.starker.presentation.navigation.routes.workout.NavWorkoutRoutes
import com.app.starker.presentation.navigation.routes.workout.WorkoutRoutes
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

@Composable
fun NavComposables(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = if (FirebaseAuth.getInstance().uid == null) AuthRoutes.RegisterScreen.route else WorkoutRoutes.ShowWorkout.route
    ) {
        NavAuthRoutes(navHostController)
        NavWorkoutRoutes(navHostController)
    }
}
