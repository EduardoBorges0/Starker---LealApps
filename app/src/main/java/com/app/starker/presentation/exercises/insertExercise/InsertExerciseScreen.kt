package com.app.starker.presentation.exercises.insertExercise

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.app.starker.data.model.ExerciseModel
import com.app.starker.presentation.common.view.LoadingOverview
import com.app.starker.presentation.common.view.TopBarView
import com.app.starker.presentation.exercises.FormExerciseView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertExerciseScreen(
    workoutId: String,
    navHostController: NavHostController,
    viewModel: InsertExerciseViewModel = hiltViewModel()
) {
    var name by remember { mutableStateOf("") }
    var observation by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val isLoading by viewModel.isLoading.collectAsState()
    val isNavigate by viewModel.isNavigate.collectAsState()

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    // Navegação controlada pelo estado do ViewModel
    LaunchedEffect(isNavigate) {
        if (isNavigate) {
            navHostController.popBackStack()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        TopBarView(navHostController)
        FormExerciseView(
            text = "Novo Exercício",
            name = name,
            onNameChange = { name = it },
            observation = observation,
            onObservationChange = { observation = it },
            imageUri = imageUri,
            onPickImage = { imagePickerLauncher.launch("image/*") },
            onSave = {
                if (name.isBlank() || observation.isBlank() || imageUri == null) {
                    errorMessage = "Erros: Preencha todos os campos."
                } else {
                    viewModel.createExerciseWithImage(
                        workoutId = workoutId,
                        exercise = ExerciseModel(
                            name = name,
                            observation = observation
                        ),
                        imageUri = imageUri!!,
                        onResult = { id ->
                            if (id == null) {
                                errorMessage = "Erro ao inserir exercício."
                            }
                        }
                    )
                    errorMessage = null
                }
            },
            errorMessage = errorMessage,
            modifier = Modifier.fillMaxSize()
        )

        errorMessage?.let {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 120.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        if (isLoading) {
            LoadingOverview()
        }
    }
}