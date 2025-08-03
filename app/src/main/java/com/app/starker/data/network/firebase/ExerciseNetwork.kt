package com.app.starker.data.network.firebase

import android.net.Uri
import com.app.starker.data.model.ExerciseModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await

class ExerciseNetwork {
    private val firestoreInstance = FirebaseFirestore.getInstance()
    private val uid = FirebaseAuth.getInstance().uid ?: ""

    suspend fun createExercise(workoutId: String, exercise: ExerciseModel): DocumentReference {
        val exerciseRef = firestoreInstance.collection("Users")
            .document(uid)
            .collection("Workout")
            .document(workoutId)
            .collection("Exercise")
            .document()
        exerciseRef.set(exercise).await()
        return exerciseRef
    }

    fun uploadImageAndReturnUrl(
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

    suspend fun editExercise(workoutId: String, exerciseId: String, updatedExercise: ExerciseModel) {
        val exerciseRef = firestoreInstance.collection("Users")
            .document(uid)
            .collection("Workout")
            .document(workoutId)
            .collection("Exercise")
            .document(exerciseId)
        exerciseRef.set(updatedExercise).await()
    }

    suspend fun deleteExercise(workoutId: String, exerciseId: String) {
        val exerciseRef = firestoreInstance.collection("Users")
            .document(uid)
            .collection("Workout")
            .document(workoutId)
            .collection("Exercise")
            .document(exerciseId)
        exerciseRef.delete().await()
    }

    suspend fun getAllExercises(workoutId: String): List<ExerciseModel> {
        val exercisesSnapshot = firestoreInstance.collection("Users")
            .document(uid)
            .collection("Workout")
            .document(workoutId)
            .collection("Exercise")
            .get()
            .await()
        return exercisesSnapshot.documents.mapNotNull { it.toObject(ExerciseModel::class.java) }
    }

    suspend fun getExerciseById(workoutId: String, exerciseId: String): ExerciseModel? {
        val exerciseRef = firestoreInstance.collection("Users")
            .document(uid)
            .collection("Workout")
            .document(workoutId)
            .collection("Exercise")
            .document(exerciseId)
            .get()
            .await()
        return exerciseRef.toObject(ExerciseModel::class.java)
    }
}
