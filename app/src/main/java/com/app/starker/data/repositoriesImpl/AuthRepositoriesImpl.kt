package com.app.starker.data.repositoriesImpl

import com.app.starker.data.network.firebase.AuthNetwork
import com.app.starker.domain.repositories.AuthRepositories
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.DocumentReference
import javax.inject.Inject

class AuthRepositoriesImpl @Inject constructor(private val authNetwork: AuthNetwork) :
    AuthRepositories {
    override suspend fun registerUser(email: String, password: String) {
        authNetwork.registerUser(email, password)
    }

    override suspend fun loginUser(
        email: String,
        password: String
    ): AuthResult {
        return authNetwork.loginUser(email, password)
    }

    override fun logout() {
        authNetwork.logout()
    }

    override fun isUserLoggedIn(): Boolean {
        return authNetwork.isUserLoggedIn()
    }

    override fun getUserUid(): String? {
        return authNetwork.getUserUid()
    }

    override suspend fun createUserInFirestore(
        username: String,
        email: String
    ): DocumentReference {
        return authNetwork.createUserInFirestore(username, email)
    }
}