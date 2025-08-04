package com.app.starker.domain.useCase

import android.util.Log
import com.app.starker.data.model.WorkoutModel
import com.app.starker.domain.repositories.WorkoutRepositories
import com.app.starker.presentation.common.utils.TransformStringInTimestamp
import javax.inject.Inject

class UpdateWorkoutUseCase @Inject constructor(private val workoutRepositories: WorkoutRepositories) {
    suspend operator fun invoke(workoutId: String, workoutName: String, workoutDescription: String, date: String) {
        val timestamp = TransformStringInTimestamp().invoke(date)
        Log.d("AQUI O CORPO PO", "TOME TOME $timestamp")

        val workout = timestamp?.let {
            WorkoutModel(
                name = workoutName,
                description = workoutDescription,
                date = it
            )
        }
        workout?.let { workoutRepositories.editWorkoutByUser(workoutId, it) }
    }
}