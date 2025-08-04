package com.app.starker.presentation.workouts.editWorkout

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.app.starker.presentation.common.utils.TransformTimestampInString
import com.app.starker.presentation.navigation.routes.workout.WorkoutRoutes
import com.app.starker.presentation.workouts.common.view.WorkoutFormScreen

@Composable
fun UpdateWorkoutScreen(
    navHostController: NavHostController,
    workoutId: String,
    workoutNameCurrent: String,
    workoutDescriptionCurrent: String,
    dateCurrent: String
) {
    var workoutName by remember { mutableStateOf(workoutNameCurrent) }
    var workoutDescription by remember { mutableStateOf(workoutDescriptionCurrent) }
    var selectedDate by remember { mutableStateOf(dateCurrent) }
    val updateWorkoutViewModel: UpdateWorkoutViewModel = hiltViewModel()
    val isNavigate by updateWorkoutViewModel.isNavigate.collectAsState()
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
            updateWorkoutViewModel.updateWorkoutByUser(
                workoutId,
                workoutName,
                workoutDescription,
                selectedDate
            )
        },
        isNavigate = isNavigate,
        navigationRoute = {
            navHostController.navigate(WorkoutRoutes.ShowWorkout.route) {
                popUpTo(0) { inclusive = true }
            }
        },
        buttonText = "Atualizar"
    )
}