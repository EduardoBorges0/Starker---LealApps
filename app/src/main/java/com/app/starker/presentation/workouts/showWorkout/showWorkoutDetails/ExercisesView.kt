package com.app.starker.presentation.workouts.showWorkout.showWorkoutDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.app.starker.data.model.ExerciseModel

@Composable
fun ExercisesView(
    i: Int,
    exercise: List<ExerciseModel>,
    onEdit: (ExerciseModel) -> Unit = {},
    onDelete: (ExerciseModel) -> Unit = {}
) {
    val item = exercise[i]
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = if (i == 0) 32.dp else 12.dp,
                bottom = if (i == exercise.size - 1) 120.dp else 8.dp,
                start = 24.dp,
                end = 24.dp
            ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = item.image,
                contentDescription = "Imagem do exercício",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                if (item.observation.isNotBlank()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = item.observation,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Outlined.Edit,
                contentDescription = "Editar exercício",
                modifier = Modifier
                    .size(28.dp)
                    .clickable { onEdit(item) }
                    .padding(4.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(16.dp))

            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = "Deletar exercício",
                modifier = Modifier
                    .size(28.dp)
                    .clickable { onDelete(item) }
                    .padding(4.dp),
                tint = MaterialTheme.colorScheme.error
            )
        }
    }
}