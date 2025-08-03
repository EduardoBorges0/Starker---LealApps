package com.app.starker.presentation.workouts.showWorkout.showWorkoutDetails

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.app.starker.R
import com.app.starker.presentation.navigation.routes.workout.WorkoutRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowWorkoutDetailsScreen(navHostController: NavHostController, workoutId: String) {
    val viewModel: ShowWorkoutDetailsViewModel = hiltViewModel()
    val workoutById = viewModel.workoutById.collectAsState()
    var isDeleteWorkout by remember { mutableStateOf(false) }

    LaunchedEffect(workoutId) {
        if (workoutId.isNotEmpty()) {
            viewModel.getWorkoutById(workoutId)
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Text(
                    workoutById.value?.name ?: "Sem nome",
                    modifier = Modifier.padding(start = 32.dp, top = 32.dp)
                )
            },
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
            actions = {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "Excluir",
                    modifier = Modifier
                        .padding(end = 32.dp, top = 32.dp)
                        .clickable {
                            isDeleteWorkout = true
                        },
                    tint = Color.Red
                )
            },
            modifier = Modifier.background(Color.Blue)
        )

        Column(modifier = Modifier.align(Alignment.TopCenter)) {
            Spacer(modifier = Modifier.height(150.dp))
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    workoutById.value?.description ?: "Sem descrição",
                    modifier = Modifier.padding(horizontal = 40.dp)
                )
                Icon(
                    Icons.Filled.Edit,
                    null
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Divider(
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }
        AlertDialogWorkoutDetails(
            isDeleteWorkout = isDeleteWorkout,
            onDeleteWorkout = {
                viewModel.deleteWorkoutById(workoutId)
                navHostController.navigate(WorkoutRoutes.ShowWorkout.route) {
                    popUpTo(0) {
                        inclusive = true
                    }
                }
            }
        )
    }
}