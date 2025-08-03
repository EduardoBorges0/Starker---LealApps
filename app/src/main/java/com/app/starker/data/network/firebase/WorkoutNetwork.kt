package com.app.starker.data.network.firebase

import android.net.Uri
import android.util.Log
import com.app.starker.data.model.WorkoutModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await

class WorkoutNetwork {
    private val firestoreInstance = FirebaseFirestore.getInstance()
    private val uid = FirebaseAuth.getInstance().uid ?: ""

    suspend fun createWorkoutByUser(workout: WorkoutModel): DocumentReference {
        val userRef = firestoreInstance.collection("Users").document(uid)
        val workoutRef = userRef.collection("Workout").document()
        workoutRef.set(workout).await()
        return workoutRef
    }
    fun uploadImageAndReturnUrlWorkout(
        imageUri: Uri,
        documentId: String,
        onSuccess: (String) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val uid = FirebaseAuth.getInstance().uid ?: run {
            onFailure(Exception("Usuário não autenticado"))
            return
        }

        val storageRef = FirebaseStorage.getInstance().reference
        val imageRef = storageRef.child("$uid/$documentId.jpg")

        imageRef.putFile(imageUri)
            .addOnSuccessListener {
                imageRef.downloadUrl.addOnSuccessListener { downloadUri ->
                    val imageUrl = downloadUri.toString()
                    onSuccess(imageUrl)
                }.addOnFailureListener {
                    onFailure(it)
                }
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }
    suspend fun editWorkoutByUser(workoutId: String, updatedWorkout: WorkoutModel) {
        val workoutRef = firestoreInstance.collection("Users")
            .document(uid)
            .collection("Workout")
            .document(workoutId)
        workoutRef.set(updatedWorkout).await()
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