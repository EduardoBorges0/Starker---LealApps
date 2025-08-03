package com.app.starker.domain.useCase

import android.net.Uri
import android.util.Log
import com.app.starker.data.model.WorkoutModel
import com.app.starker.domain.repositories.WorkoutRepositories
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class InsertWorkoutUseCase @Inject constructor(private val workoutRepositories: WorkoutRepositories) {
    suspend operator fun invoke(
        workoutName: String,
        workoutDescription: String,
        date: String,
    ) {
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val timestamp = try {
            val date = format.parse(date)
            if (date != null) Timestamp(date) else null
        } catch (e: Exception) {
            null
        }
        val workout = timestamp?.let {
            WorkoutModel(
                name = workoutName,
                description = workoutDescription,
                date = it
            )
        }
        workout?.let { workoutRepositories.createWorkoutByUser(it) }
    }
}