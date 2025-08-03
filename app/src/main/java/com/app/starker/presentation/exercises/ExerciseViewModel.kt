package com.app.starker.presentation.exercises

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.starker.data.network.firebase.ExerciseNetwork
import com.app.starker.domain.useCase.InsertWorkoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val exerciseNetwork: ExerciseNetwork,
    private val insertWorkoutUseCase: InsertWorkoutUseCase
) :
    ViewModel() {
    private val _isImageError = MutableStateFlow<Boolean>(false)
    val isImageError : StateFlow<Boolean> = _isImageError

    private val _isNavigate = MutableStateFlow<Boolean>(false)
    val isNavigate : StateFlow<Boolean> = _isNavigate

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading : StateFlow<Boolean> = _isLoading

    fun insertExercise(
        imageUri: Uri,
        photoName: String,
        name: String,
        description: String,
        date: String
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            exerciseNetwork.uploadImageAndReturnUrl(
                imageUri,
                photoName,
                onSuccess = { imageUrl ->
                    viewModelScope.launch(Dispatchers.IO) {
                        try {
                            insertWorkoutUseCase.invoke(name, description, date)
                            _isNavigate.value = true
                        } catch (e: Exception) {

                        }
                    }
                },
                onFailure = { e ->
                    _isImageError.value = true
                }
            )
            _isLoading.value = false
        }
    }

}