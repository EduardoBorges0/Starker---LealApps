package com.app.starker.data.network.firebase

import com.app.starker.data.model.UserModel
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AuthNetwork {
    private val auth = FirebaseAuth.getInstance()
    private val firestoreInstance = FirebaseFirestore.getInstance();


    suspend fun registerUser(email: String, password: String): AuthResult {
        return auth.createUserWithEmailAndPassword(email, password).await()
    }

    suspend fun loginUser(email: String, password: String): AuthResult {
        return auth.signInWithEmailAndPassword(email, password).await()
    }

    fun logout() {
        auth.signOut()
    }

    fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    fun getUserUid(): String? {
        return auth.currentUser?.uid
    }

    suspend fun createUserInFirestore(username: String, email: String, uid: String): DocumentReference {
        val user = UserModel(
            username = username,
            email = email,
            createdAt = System.currentTimeMillis()
        )

        val userRef = firestoreInstance.collection("Users").document(uid)
        userRef.set(user).await()
        return userRef
    }

}