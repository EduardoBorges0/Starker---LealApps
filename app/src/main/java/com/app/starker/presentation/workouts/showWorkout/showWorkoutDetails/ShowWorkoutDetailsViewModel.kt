package com.app.starker.presentation.workouts.showWorkout.showWorkoutDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.starker.data.model.ExerciseModel
import com.app.starker.data.model.WorkoutModel
import com.app.starker.domain.repositories.ExerciseRepositories
import com.app.starker.domain.repositories.WorkoutRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowWorkoutDetailsViewModel @Inject constructor(
    private val workoutRepositories: WorkoutRepositories,
    private val exerciseRepositories: ExerciseRepositories
) :
    ViewModel() {
    private val _workoutById = MutableStateFlow<WorkoutModel?>(null)
    val workoutById: StateFlow<WorkoutModel?> = _workoutById

    private val _exercises: MutableStateFlow<List<ExerciseModel>> = MutableStateFlow(emptyList())
    val exercises: StateFlow<List<ExerciseModel>> = _exercises

    private val _hasError = MutableStateFlow(false)
    val hasError: StateFlow<Boolean> = _hasError

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun setHasError(value: Boolean) {
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
                exerciseRepositories.deleteAllExercises(workoutId)
                exerciseRepositories.deleteAllImagesFromWorkout(workoutId)
                _hasError.value = false
            } catch (e: Exception) {
                _hasError.value = true
            }
        }
    }

    fun deleteExercise(workoutId: String, exerciseId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                exerciseRepositories.deleteExercise(workoutId, exerciseId)
                exerciseRepositories.deleteExerciseImage(workoutId, exerciseId)
                getAllExercises(workoutId)
                getWorkoutById(workoutId)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getAllExercises(workoutId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _exercises.value = exerciseRepositories.getAllExercises(workoutId)
            } finally {
                _isLoading.value = false
            }
        }
    }
}