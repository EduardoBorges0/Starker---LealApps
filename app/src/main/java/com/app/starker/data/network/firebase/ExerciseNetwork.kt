package com.app.starker.data.network.firebase

import com.app.starker.data.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ExerciseNetwork {
    private val firestoreInstance = FirebaseFirestore.getInstance();
    private val uuid = FirebaseAuth.getInstance().uid ?: ""
    suspend fun createExerciseByUser(): DocumentReference {
        return firestoreInstance.collection("Users").document(uuid)
    }

}