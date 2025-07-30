package com.app.starker.data.network.firebase

import android.util.Log
import com.app.starker.data.model.WorkoutModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class WorkoutNetwork {
    private val firestoreInstance = FirebaseFirestore.getInstance()
    private val uid = FirebaseAuth.getInstance().uid ?: ""

    suspend fun createWorkoutByUser(workout: WorkoutModel): DocumentReference {
        Log.d("UUID", "ESTE É O UUID $uid")
        val userRef = firestoreInstance.collection("Users").document(uid)
        val workoutRef = userRef.collection("Workout").document()
        workoutRef.set(workout).await()  // espera a gravação completar
        return workoutRef
    }

    suspend fun editWorkoutByUser(workoutId: String, updatedWorkout: WorkoutModel) {
        val workoutRef = firestoreInstance.collection("Users")
            .document(uid)
            .collection("Workout")
            .document(workoutId)
        workoutRef.set(updatedWorkout).await() // Sobrescreve o documento
    }

    suspend fun deleteWorkoutByUser(workoutId: String) {
        val workoutRef = firestoreInstance.collection("Users")
            .document(uid)
            .collection("Workout")
            .document(workoutId)
        workoutRef.delete().await()
    }

    suspend fun getEveryWorkoutByUser(): List<WorkoutModel> {
        val workoutCollection = firestoreInstance.collection("Users")
            .document(uid)
            .collection("Workout")
            .get()
            .await()
        Log.d("WORKOUT", "ESSES SÃO OS WORKOUT ${workoutCollection.documents.mapNotNull { it.toObject(WorkoutModel::class.java) }}")

        return workoutCollection.documents.mapNotNull { it.toObject(WorkoutModel::class.java) }
    }

    suspend fun getWorkoutById(workoutId: String): WorkoutModel? {
        val workoutRef = firestoreInstance.collection("Users")
            .document(uid)
            .collection("Workout")
            .document(workoutId)
            .get()
            .await()
        return workoutRef.toObject(WorkoutModel::class.java)
    }

}