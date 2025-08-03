package com.app.starker.presentation.workouts.showWorkout

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.app.starker.R
import com.app.starker.data.model.WorkoutModel
import com.app.starker.presentation.navigation.routes.workout.WorkoutRoutes
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun WorkoutView(workouts: List<WorkoutModel>, i: Int, isClick: () -> Unit) {
    val timestamp = workouts[i].date
    val date = Date(timestamp.seconds * 1000)

    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val formattedDate = formatter.format(date)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = if (i == 0) 150.dp else 20.dp,
                bottom = if (i == workouts.size - 1) 74.dp else 4.dp,
                start = 10.dp,
                end = 10.dp
            )
            .height(80.dp)
            .clip(RoundedCornerShape(80.dp))
            .background(MaterialTheme.colorScheme.secondary)
            .clickable {
                isClick()
            }
    ) {
        AsyncImage(
            model = R.drawable.icon_workout,
            contentDescription = null,
            modifier = Modifier
                .height(130.dp)
                .aspectRatio(1f)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp)
        ){
            Text(
                workouts[i].name,
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                formattedDate,
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }

}