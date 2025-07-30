package com.app.starker.data.network.firebase

import com.app.starker.data.model.WorkoutModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class WorkoutNetwork {
    private val firestoreInstance = FirebaseFirestore.getInstance();
    private val uid = FirebaseAuth.getInstance().uid ?: ""
    suspend fun createWorkoutByUser(workout : WorkoutModel): DocumentReference {
        val userRef = firestoreInstance.collection("Users").document(uid)
        val workoutRef = userRef.collection("Workout").document()
        workoutRef.set({
          workout
        })
        return workoutRef
    }
    suspend fun editWorkoutByUser(){

    }
    suspend fun deleteWorkoutByUser(){

    }
    suspend fun getEveryWorkoutByUser(){

    }
    suspend fun getWorkoutById(){

    }
}