package com.app.starker.presentation.auth.login

import androidx.lifecycle.ViewModel
import com.app.starker.data.network.firebase.AuthNetwork
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authNetwork: AuthNetwork) : ViewModel() {

}