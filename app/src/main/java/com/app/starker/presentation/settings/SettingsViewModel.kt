package com.app.starker.presentation.settings

import androidx.lifecycle.ViewModel
import com.app.starker.domain.repositories.AuthRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val authRepositories: AuthRepositories) :
    ViewModel() {
    fun logout() {
        authRepositories.logout()
    }
}