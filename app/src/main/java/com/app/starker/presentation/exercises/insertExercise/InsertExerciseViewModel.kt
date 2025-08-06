package com.app.starker.presentation.exercises.insertExercise

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.starker.data.model.ExerciseModel
import com.app.starker.domain.repositories.ExerciseRepositories
import com.app.starker.domain.useCase.InsertWorkoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertExerciseViewModel @Inject constructor(
    private val exerciseRepositories: ExerciseRepositories,
    private val insertWorkoutUseCase: InsertWorkoutUseCase
) : ViewModel() {
    private val _isImageError = MutableStateFlow(false)
    val isImageError: StateFlow<Boolean> = _isImageError

    private val _isNavigate = MutableStateFlow(false)
    val isNavigate: StateFlow<Boolean> = _isNavigate

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun createExerciseWithImage(
        workoutId: String,
        exercise: ExerciseModel,
        imageUri: Uri,
        onResult: (String?) -> Unit
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val exerciseId = exerciseRepositories.createExercise(workoutId, exercise.copy(image = ""))
                if (exerciseId != null) {
                    val imageUrl = exerciseRepositories.uploadExerciseImage(workoutId, exerciseId, imageUri)
                    val updatedExercise = exercise.copy(id = exerciseId, image = imageUrl)
                    exerciseRepositories.editExercise(workoutId, exerciseId, updatedExercise)
                    _isNavigate.value = true
                    onResult(exerciseId)
                } else {
                    _isNavigate.value = false
                    onResult(null)
                }
            } catch (e: Exception) {
                _isNavigate.value = false
                onResult(null)
            } finally {
                _isLoading.value = false
            }
        }
    }
}