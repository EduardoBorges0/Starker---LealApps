package com.app.starker.domain.repositories

import android.net.Uri
import com.app.starker.data.model.WorkoutModel
import com.google.firebase.firestore.DocumentReference

interface WorkoutRepositories {
    suspend fun createWorkoutByUser(workout: WorkoutModel): DocumentReference;
    suspend fun editWorkoutByUser(workoutId: String, updatedWorkout: WorkoutModel);
    suspend fun deleteWorkoutByUser(workoutId: String);
    suspend fun getEveryWorkoutByUser(): List<WorkoutModel>;
    suspend fun getWorkoutById(workoutId: String): WorkoutModel?;
    fun uploadImageAndReturnUrlWorkout(
        imageUri: Uri,
        documentId: String,
        onSuccess: (String) -> Unit,
        onFailure: (Exception) -> Unit
    )
}