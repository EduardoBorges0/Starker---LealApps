package com.app.starker.presentation.exercises.updateExercise

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.app.starker.presentation.exercises.FormInsertExerciseView
import androidx.core.net.toUri
import com.app.starker.presentation.common.view.LoadingOverview
import com.app.starker.presentation.common.view.TopBarView
import com.app.starker.R

@Composable
fun UpdateExerciseScreen(
  workoutId: String,
  exerciseId: String,
  navHostController: NavHostController
) {
    val viewModel: UpdateExerciseViewModel = hiltViewModel()
    val fillFields = stringResource(R.string.error_fill_all_fields)
    val noChangesDetected = stringResource(R.string.error_no_changes_detected)
    val exercise by viewModel.exercise.collectAsState()
    var name by remember { mutableStateOf("") }
    var observation by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val isNavigate by viewModel.isNavigate.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val newImageUri = if (imageUri?.toString()?.startsWith("http") == true) null else imageUri

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }
    LaunchedEffect(workoutId) {
        viewModel.getExerciseById(workoutId, exerciseId)
    }
    LaunchedEffect(isNavigate) {
        if(isNavigate){
            navHostController.popBackStack()
        }
    }

    LaunchedEffect(exercise) {
        exercise?.let {
            name = it.name
            observation = it.observation
            imageUri = it.image.toUri()
        }
    }

    TopBarView(navHostController)
    FormInsertExerciseView(
        text = stringResource(R.string.update_exercise_title),
        name = name,
        onNameChange = { name = it },
        observation = observation,
        onObservationChange = { observation = it },
        imageUri = imageUri,
        onPickImage = { imagePickerLauncher.launch("image/*") },
        onSave = {
            if (name.isBlank() || observation.isBlank()) {
                errorMessage = fillFields
            } else if (exercise != null &&
                name == exercise!!.name &&
                observation == exercise!!.observation &&
                imageUri?.toString() == exercise!!.image
            ) {
                errorMessage = noChangesDetected
            } else {
                viewModel.updateExercise(
                    workoutId = workoutId,
                    exerciseId = exerciseId,
                    updatedName = name,
                    updatedObservation = observation,
                    updatedImage = newImageUri
                )
                errorMessage = null
            }
        },
        errorMessage = errorMessage,
        modifier = Modifier.fillMaxSize()
    )
    if(isLoading){
        LoadingOverview()
    }
}