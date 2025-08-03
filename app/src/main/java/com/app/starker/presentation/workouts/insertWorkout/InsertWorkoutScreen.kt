package com.app.starker.presentation.workouts.insertWorkout

import android.app.DatePickerDialog // ✅ IMPORTANTE!
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.app.starker.R
import com.app.starker.presentation.common.view.CustomTextField
import com.app.starker.presentation.navigation.routes.workout.WorkoutRoutes
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("RememberReturnType")
@Composable
fun InsertWorkoutScreen(navHostController: NavHostController) {
    var workoutName by remember { mutableStateOf("") }
    var workoutDescription by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }
    var isEmptyFields by remember { mutableStateOf(false) }
    val viewModel: InsertWorkoutViewModel = hiltViewModel()
    val isNavigate by viewModel.isNavigate.collectAsState()
    val context = LocalContext.current
    val calendar = remember { Calendar.getInstance() }

    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                selectedDate = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }
    LaunchedEffect(isNavigate) {
        if (isNavigate) {
            navHostController.navigate(WorkoutRoutes.ShowWorkout.route) {
                popUpTo(0) { inclusive = true }
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {},
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            navigationIcon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back_button_description),
                    modifier = Modifier
                        .padding(start = 32.dp, top = 32.dp)
                        .clickable {
                            navHostController.popBackStack()
                        }
                )
            },
            modifier = Modifier.background(Color.Blue)
        )
        Column(modifier = Modifier.align(Alignment.Center)) {
            CustomTextField(
                value = workoutName,
                onValueChange = { workoutName = it },
                placeholder = "Nome do treino"
            )
            Spacer(modifier = Modifier.height(20.dp))

            CustomTextField(
                value = workoutDescription,
                onValueChange = { workoutDescription = it },
                placeholder = "Descrição do treino"
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    datePickerDialog.show()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.secondary
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
            ) {
                Text(
                    if (selectedDate.isNotEmpty()) "Data: $selectedDate" else "Selecionar data",
                    modifier = Modifier.padding(10.dp)
                )

            }
        }
        Button(
            onClick = {
                isEmptyFields =
                    workoutName.isEmpty() || workoutDescription.isEmpty() || selectedDate.isEmpty()
                if (!isEmptyFields) {
//                    navHostController.navigate(
//                        WorkoutRoutes.InsertWorkoutImage.createRoute(
//                            workoutName,
//                            workoutDescription,
//                            selectedDate
//                        )
//                    )
                    viewModel.insertWorkout(workoutName, workoutDescription, selectedDate)

                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text("Próximo", color = MaterialTheme.colorScheme.secondary)
        }
        if (isEmptyFields) {
            AlertDialogInsertWorkout(
                isEmptyField = isEmptyFields,
                onEmptyField = {
                    isEmptyFields = it
                }
            )
        }
    }
}
