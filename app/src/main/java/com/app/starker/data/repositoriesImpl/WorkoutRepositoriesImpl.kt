package com.app.starker.data.repositoriesImpl

import com.app.starker.data.model.WorkoutModel
import com.app.starker.data.network.firebase.WorkoutNetwork
import com.app.starker.domain.repositories.WorkoutRepositories
import com.google.firebase.firestore.DocumentReference
import javax.inject.Inject

class WorkoutRepositoriesImpl @Inject constructor(private val workoutNetwork: WorkoutNetwork) :
    WorkoutRepositories {
    override suspend fun createWorkoutByUser(workout: WorkoutModel): DocumentReference {
        return workoutNetwork.createWorkoutByUser(workout);
    }

    override suspend fun editWorkoutByUser(
        workoutId: String,
        updatedWorkout: WorkoutModel
    ) {
        workoutNetwork.editWorkoutByUser(workoutId, updatedWorkout)
    }

    override suspend fun deleteWorkoutByUser(workoutId: String) {
        workoutNetwork.deleteWorkoutByUser(workoutId)
    }

    override suspend fun getEveryWorkoutByUser(): List<WorkoutModel> {
        return workoutNetwork.getEveryWorkoutByUser()
    }

    override suspend fun getWorkoutById(workoutId: String): WorkoutModel? {
        return workoutNetwork.getWorkoutById(workoutId)
    }
}