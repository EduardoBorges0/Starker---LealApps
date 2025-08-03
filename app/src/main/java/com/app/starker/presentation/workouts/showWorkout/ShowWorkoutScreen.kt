package com.app.starker.presentation.workouts.showWorkout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.app.starker.R
import com.app.starker.presentation.common.view.LoadingOverview
import com.app.starker.presentation.navigation.routes.workout.WorkoutRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowWorkoutScreen(navHostController: NavHostController) {
    val showWorkoutViewModel: ShowWorkoutViewModel = hiltViewModel()
    val workouts = showWorkoutViewModel.getAllWorkouts.collectAsState()
    val isLoading = showWorkoutViewModel.isLoading.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {},
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            actions = {
                Icon(
                    imageVector = Icons.Outlined.Settings, // Ícone de lixeira
                    contentDescription = "Excluir",
                    modifier = Modifier
                        .padding(end = 32.dp, top = 32.dp)
                        .clickable {

                        },
                    tint = MaterialTheme.colorScheme.secondary
                )
            },
            modifier = Modifier.background(Color.Blue)
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(workouts.value.size) { i ->
                WorkoutView(workouts = workouts.value, i = i, isClick = {
                    navHostController.navigate(WorkoutRoutes.ShowWorkoutDetails.createRoute(workouts.value[i].uid))
                })
            }
        }

        ElevatedButton(
            onClick = {
                navHostController.navigate(WorkoutRoutes.InsertWorkout.route)
            },
            colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.secondary
            ),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(32.dp)
                .size(70.dp)
        ) {
            Icon(Icons.Filled.Add, contentDescription = null, modifier = Modifier.size(40.dp))
        }
        if (isLoading.value) {
            LoadingOverview()
        }
    }
}
