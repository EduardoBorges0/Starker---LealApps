package com.app.starker.presentation.workouts.insertWorkout

import android.annotation.SuppressLint
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.app.starker.presentation.navigation.routes.workout.WorkoutRoutes
import com.app.starker.presentation.workouts.common.view.WorkoutFormScreen

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("RememberReturnType")
@Composable
fun InsertWorkoutScreen(navHostController: NavHostController) {
    var workoutName by remember { mutableStateOf("") }
    var workoutDescription by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }
    val viewModel: InsertWorkoutViewModel = hiltViewModel()
    val isNavigate by viewModel.isNavigate.collectAsState()
    WorkoutFormScreen(
        navHostController = navHostController,
        workoutName = workoutName,
        onWorkoutName = {
            workoutName = it
        },
        workoutDescription = workoutDescription,
        onWorkoutDescription = {
            workoutDescription = it
        },
        date = selectedDate,
        onDate = {
            selectedDate = it
        },
        onSubmit = {
            viewModel.insertWorkout(workoutName, workoutDescription, selectedDate)
        },
        isNavigate = isNavigate,
        navigationRoute = {
            navHostController.navigate(WorkoutRoutes.ShowWorkout.route) {
                popUpTo(0) { inclusive = true }
            }
        },
        buttonText = "Salvar"
    )
}
