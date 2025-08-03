package com.app.starker.data.repositoriesImpl

import android.net.Uri
import com.app.starker.data.model.ExerciseModel
import com.app.starker.data.network.firebase.ExerciseNetwork
import com.app.starker.domain.repositories.ExerciseRepositories
import com.google.firebase.firestore.DocumentReference
import javax.inject.Inject

class ExerciseRepositoriesImpl @Inject constructor(private val exerciseNetwork: ExerciseNetwork) :
    ExerciseRepositories {
    override suspend fun createExercise(
        workoutId: String,
        exercise: ExerciseModel
    ): DocumentReference {
        return exerciseNetwork.createExercise(workoutId, exercise)
    }

    override suspend fun editExercise(
        workoutId: String,
        exerciseId: String,
        updatedExercise: ExerciseModel
    ) {
        exerciseNetwork.editExercise(workoutId, exerciseId, updatedExercise)
    }

    override suspend fun deleteExercise(workoutId: String, exerciseId: String) {
        exerciseNetwork.deleteExercise(workoutId, exerciseId)
    }

    override suspend fun getAllExercises(workoutId: String): List<ExerciseModel> {
        return exerciseNetwork.getAllExercises(workoutId)
    }

    override suspend fun getExerciseById(
        workoutId: String,
        exerciseId: String
    ): ExerciseModel? {
        return exerciseNetwork.getExerciseById(workoutId, exerciseId)
    }

    override fun uploadImageAndReturnUrl(
        imageUri: Uri,
        documentId: String,
        onSuccess: (String) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        exerciseNetwork.uploadImageAndReturnUrl(imageUri, documentId, onSuccess, onFailure)
    }
}