package com.app.starker.domain.repositories

import android.net.Uri
import com.app.starker.data.model.ExerciseModel

interface ExerciseRepositories {
    suspend fun createExercise(workoutId: String, exercise: ExerciseModel): String?
    suspend fun editExercise(workoutId: String, exerciseId: String, updatedExercise: ExerciseModel)
    suspend fun deleteExercise(workoutId: String, exerciseId: String)
    suspend fun getAllExercises(workoutId: String): List<ExerciseModel>
    suspend fun getExerciseById(workoutId: String, exerciseId: String): ExerciseModel?
    suspend fun uploadExerciseImage(workoutId: String, exerciseId: String, imageUri: Uri): String
    suspend fun deleteAllExercises(workoutId: String)
    suspend fun deleteExerciseImage(workoutId: String, exerciseId: String)
    suspend fun deleteAllImagesFromWorkout(workoutId: String)
}