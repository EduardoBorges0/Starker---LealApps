package com.app.starker.presentation.exercises

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.app.starker.R
import com.app.starker.presentation.common.view.CustomTextField

@Composable
fun FormInsertExerciseView(
    text: String,
    name: String,
    onNameChange: (String) -> Unit,
    observation: String,
    onObservationChange: (String) -> Unit,
    imageUri: Uri?,
    onPickImage: () -> Unit,
    onSave: () -> Unit,
    errorMessage: String? = null,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 4.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            CustomTextField(
                value = name,
                onValueChange = onNameChange,
                placeholder = stringResource(R.string.exercise_name_placeholder)
            )
            Spacer(modifier = Modifier.height(20.dp))

            CustomTextField(
                value = observation,
                onValueChange = onObservationChange,
                placeholder = stringResource(R.string.exercise_observation_placeholder)
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = onPickImage,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .height(60.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(stringResource(R.string.select_image), color = MaterialTheme.colorScheme.secondary)
            }

            imageUri?.let {
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = stringResource(R.string.selected_image_content_description),
                    modifier = Modifier
                        .size(120.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
        }

        Button(
            onClick = onSave,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(0.dp)
        ) {
            Text("Salvar Exercício", color = MaterialTheme.colorScheme.secondary)
        }

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
    }
}