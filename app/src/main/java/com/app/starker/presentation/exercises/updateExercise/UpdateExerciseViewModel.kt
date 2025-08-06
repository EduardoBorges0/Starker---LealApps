package com.app.starker.presentation.exercises.updateExercise

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.starker.data.model.ExerciseModel
import com.app.starker.domain.repositories.ExerciseRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateExerciseViewModel @Inject constructor(private val exerciseRepositories: ExerciseRepositories) :
    ViewModel() {
    private val _exercise = MutableStateFlow<ExerciseModel?>(null)
    val exercise: StateFlow<ExerciseModel?> = _exercise

    private val _hasError = MutableStateFlow<Boolean>(false)
    val hasError: StateFlow<Boolean> = _hasError

    private val _isNavigate = MutableStateFlow<Boolean>(false)
    val isNavigate: StateFlow<Boolean> = _isNavigate

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    fun updateExercise(
        workoutId: String,
        exerciseId: String,
        updatedName: String,
        updatedImage: Uri?,
        updatedObservation: String
    )
    {
        viewModelScope.launch {
            _isLoading.value = true
            Log.d("TOME", "updateExercise: $workoutId, $exerciseId, $updatedName, $updatedImage, $updatedObservation")
            try {
                val currentExercise = exercise.value
                val imageUrl = if (updatedImage != null && updatedImage.toString().startsWith("content://")) {
                    // Nova imagem escolhida pelo usuário
                    exerciseRepositories.uploadExerciseImage(workoutId, exerciseId, updatedImage)
                } else {
                    // Reutiliza a imagem antiga
                    currentExercise?.image ?: ""
                }

                val exercise = ExerciseModel(
                    id = exerciseId,
                    name = updatedName,
                    image = imageUrl,
                    observation = updatedObservation
                )
                exerciseRepositories.editExercise(workoutId, exerciseId, exercise)
                _isNavigate.value = true
            } catch (e: Exception) {
                _hasError.value = true
                Log.e("UpdateExerciseViewModel", "Erro ao atualizar exercício", e)
            }

            _isLoading.value = false
        }
    }

    fun getExerciseById(
        workoutId: String,
        exerciseId: String,
    ) {
        viewModelScope.launch {
            try {
                _exercise.value = exerciseRepositories.getExerciseById(workoutId, exerciseId)
            } catch (e: Exception) {
                _hasError.value = true
            }
        }
    }
}