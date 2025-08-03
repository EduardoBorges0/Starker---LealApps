package com.app.starker.presentation.auth.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.starker.domain.repositories.AuthRepositories
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authRepositories: AuthRepositories) :
    ViewModel() {
    private val _isFirebaseColision = MutableStateFlow<Boolean>(false)
    val isFirebaseColision: StateFlow<Boolean> = _isFirebaseColision

    private val _isBadFormatted = MutableStateFlow<Boolean>(false)
    val isBadFormatted: StateFlow<Boolean> = _isBadFormatted

    private val _isError = MutableStateFlow<Boolean>(false)
    val isError: StateFlow<Boolean> = _isError

    private val _isNavigate = MutableStateFlow<Boolean>(false)
    val isNavigate: StateFlow<Boolean> = _isNavigate

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun setFirebaseCollision(value: Boolean) {
        _isFirebaseColision.value = value
    }

    fun setBadFormatted(value: Boolean) {
        _isBadFormatted.value = value
    }

    fun setError(value: Boolean) {
        _isError.value = value
    }

    fun registerUser(username: String, email: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                authRepositories.registerUser(email, password)
                val uid = authRepositories.getUserUid()
                if (uid != null) {
                    authRepositories.createUserInFirestore(username, email, uid)
                }
                _isNavigate.value = true
            } catch (e: FirebaseAuthUserCollisionException) {
                _isFirebaseColision.value = true
            } catch (e: FirebaseAuthInvalidCredentialsException) {
                _isBadFormatted.value = true
            } catch (e: Exception) {
                _isError.value = true
            }
            _isLoading.value = false
        }
    }
}