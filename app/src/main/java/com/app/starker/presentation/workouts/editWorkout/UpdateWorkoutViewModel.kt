package com.app.starker.presentation.workouts.editWorkout

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.starker.domain.useCase.UpdateWorkoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateWorkoutViewModel @Inject constructor(private val updateWorkoutUseCase: UpdateWorkoutUseCase) :
    ViewModel() {
    private val _isNavigate = MutableStateFlow<Boolean>(false)
    val isNavigate: StateFlow<Boolean> = _isNavigate

    private val _isError = MutableStateFlow<Boolean>(false)
    val isError: StateFlow<Boolean> = _isError

    fun updateWorkoutByUser(
        workoutId: String,
        workoutName: String,
        workoutDescription: String,
        date: String
    ) {
        viewModelScope.launch {
            try {
                updateWorkoutUseCase.invoke(workoutId, workoutName, workoutDescription, date)
                _isNavigate.value = true
            }catch (e: Exception){
                _isError.value = true
            }
        }
    }
}