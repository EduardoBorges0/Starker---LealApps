package com.app.starker.presentation.workouts.showWorkout.showWorkoutDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.starker.data.model.WorkoutModel
import com.app.starker.domain.repositories.WorkoutRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowWorkoutDetailsViewModel @Inject constructor(private val workoutRepositories: WorkoutRepositories) :
    ViewModel() {
    private val _workoutById = MutableStateFlow<WorkoutModel?>(null)
    val workoutById: StateFlow<WorkoutModel?> = _workoutById

    private val _hasError = MutableStateFlow(false)
    val hasError: StateFlow<Boolean> = _hasError

    fun setHasError(value: Boolean){
        _hasError.value = value
    }
    fun getWorkoutById(workoutId: String) {
        viewModelScope.launch {
            try {
                _workoutById.value = workoutRepositories.getWorkoutById(workoutId)
                _hasError.value = false
            } catch (e: Exception) {
                _hasError.value = true
            }
        }
    }

    fun deleteWorkoutById(workoutId: String) {
        viewModelScope.launch {
            try {
                workoutRepositories.deleteWorkoutByUser(workoutId)
                _hasError.value = false
            } catch (e: Exception) {
                _hasError.value = true
            }
        }
    }
}