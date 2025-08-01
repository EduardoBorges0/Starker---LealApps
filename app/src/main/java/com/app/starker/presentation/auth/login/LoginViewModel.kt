package com.app.starker.presentation.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.starker.data.network.firebase.AuthNetwork
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authNetwork: AuthNetwork) : ViewModel() {
    private val _isAuthInvalid = MutableStateFlow<Boolean>(false)
    val isAuthInvalid : StateFlow<Boolean> = _isAuthInvalid

    private val _isError = MutableStateFlow<Boolean>(false)
    val isError : StateFlow<Boolean> = _isError

    private val _isNavigate = MutableStateFlow<Boolean>(false)
    val isNavigate : StateFlow<Boolean> = _isNavigate

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading : StateFlow<Boolean> = _isLoading

    fun setAuthInvalid(value: Boolean){
        _isAuthInvalid.value = value
    }
    fun setIsError(value: Boolean){
        _isError.value = value
    }
    fun loginUser(email: String, password: String){
        viewModelScope.launch {
            _isLoading.value = true
            try {
                authNetwork.loginUser(email, password)
                _isNavigate.value = true
            }catch(e: FirebaseAuthInvalidCredentialsException){
                _isAuthInvalid.value = true
            }
            catch (e: Exception){
                _isError.value = true
            }
            _isLoading.value = false
        }
    }
}