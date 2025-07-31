package com.app.starker.domain.repositories

import com.app.starker.data.model.ExerciseModel
import com.google.firebase.firestore.DocumentReference

interface ExerciseRepositories {
    suspend fun createExercise(workoutId: String, exercise: ExerciseModel): DocumentReference;
    suspend fun editExercise(workoutId: String, exerciseId: String, updatedExercise: ExerciseModel)
    suspend fun deleteExercise(workoutId: String, exerciseId: String)
    suspend fun getAllExercises(workoutId: String): List<ExerciseModel>
    suspend fun getExerciseById(workoutId: String, exerciseId: String): ExerciseModel?
}