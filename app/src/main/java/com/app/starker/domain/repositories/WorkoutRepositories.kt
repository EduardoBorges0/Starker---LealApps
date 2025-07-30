package com.app.starker.domain.repositories

import com.app.starker.data.model.WorkoutModel
import com.google.firebase.firestore.DocumentReference

interface WorkoutRepositories {
    suspend fun createWorkoutByUser(workout : WorkoutModel): DocumentReference;
}