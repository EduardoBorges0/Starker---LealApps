package com.app.starker.data.repositoriesImpl

import android.net.Uri
import com.app.starker.data.model.ExerciseModel
import com.app.starker.data.network.firebase.ExerciseNetwork
import com.app.starker.domain.repositories.ExerciseRepositories
import javax.inject.Inject

class ExerciseRepositoriesImpl @Inject constructor(private val exerciseNetwork: ExerciseNetwork) :
    ExerciseRepositories {

    override suspend fun createExercise(
        workoutId: String,
        exercise: ExerciseModel
    ): String? {
        return exerciseNetwork.createExercise(workoutId, exercise)
    }

    override suspend fun editExercise(
        workoutId: String,
        exerciseId: String,
        updatedExercise: ExerciseModel
    ) {
        // Cria uma cópia do exercício com o id correto
        val exerciseWithId = updatedExercise.copy(id = exerciseId)
        exerciseNetwork.updateExercise(workoutId, exerciseWithId)
    }

    override suspend fun deleteExercise(workoutId: String, exerciseId: String) {
        exerciseNetwork.deleteExercise(workoutId, exerciseId)
    }

    override suspend fun getAllExercises(workoutId: String): List<ExerciseModel> {
        return exerciseNetwork.getEveryExercise(workoutId)
    }

    override suspend fun getExerciseById(
        workoutId: String,
        exerciseId: String
    ): ExerciseModel? {
        return exerciseNetwork.getExerciseById(workoutId, exerciseId)
    }

    override suspend fun uploadExerciseImage(
        workoutId: String,
        exerciseId: String,
        imageUri: Uri
    ): String {
        return exerciseNetwork.uploadExerciseImage(workoutId, exerciseId, imageUri)
    }

    override suspend fun deleteAllExercises(workoutId: String) {
        return exerciseNetwork.deleteAllExercises(workoutId)
    }

    override suspend fun deleteExerciseImage(workoutId: String, exerciseId: String) {
        return exerciseNetwork.deleteExerciseImage(workoutId, exerciseId)
    }

    override suspend fun deleteAllImagesFromWorkout(workoutId: String) {
        return exerciseNetwork.deleteAllImagesFromWorkout(workoutId)
    }


}