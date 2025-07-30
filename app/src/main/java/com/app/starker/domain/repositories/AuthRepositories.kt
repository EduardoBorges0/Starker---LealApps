package com.app.starker.domain.repositories

import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.DocumentReference

interface AuthRepositories {
    suspend fun registerUser(email: String, password: String);
    suspend fun loginUser(email: String, password: String): AuthResult;
    fun logout()
    fun isUserLoggedIn(): Boolean
    fun getUserUid(): String?
    suspend fun createUserInFirestore(
        username: String, email: String, uid: String
    ): DocumentReference
}