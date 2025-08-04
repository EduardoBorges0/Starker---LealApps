package com.app.starker.presentation.workouts.common.view

import com.app.starker.presentation.workouts.insertWorkout.AlertDialogInsertWorkout
import com.app.starker.presentation.workouts.insertWorkout.InsertWorkoutViewModel
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
@SuppressLint("RememberReturnType", "DefaultLocale")
@Composable
fun WorkoutFormScreen(
    navHostController: NavHostController,
    workoutName: String,
    onWorkoutName: (String) -> Unit,
    workoutDescription: String,
    onWorkoutDescription: (String) -> Unit,
    date: String,
    onDate: (String) -> Unit,
    onSubmit: () -> Unit,
    isNavigate: Boolean,
    navigationRoute: () -> Unit,
    buttonText: String
) {
    var isEmptyFields by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val calendar = remember { Calendar.getInstance() }

    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                onDate(String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }
    LaunchedEffect(isNavigate) {
        if (isNavigate) {
            navigationRoute()
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
                onValueChange = { onWorkoutName(it) },
                placeholder = "Nome do treino"
            )
            Spacer(modifier = Modifier.height(20.dp))

            CustomTextField(
                value = workoutDescription,
                onValueChange = { onWorkoutDescription(it) },
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
                    if (date.isNotEmpty()) "Data: $date" else "Selecionar data",
                    modifier = Modifier.padding(10.dp)
                )

            }
        }
        Button(
            onClick = {
                isEmptyFields =
                    workoutName.isEmpty() || workoutDescription.isEmpty() || date.isEmpty()
                if (!isEmptyFields) {
                    onSubmit()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(buttonText, color = MaterialTheme.colorScheme.secondary)
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
