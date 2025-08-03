package com.app.starker.presentation.workouts.showWorkout

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
class ShowWorkoutViewModel @Inject constructor(private val workoutRepositories: WorkoutRepositories) :
    ViewModel() {
    private val _getAllWorkouts = MutableStateFlow<List<WorkoutModel>>(emptyList())
    val getAllWorkouts: StateFlow<List<WorkoutModel>> = _getAllWorkouts

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading : StateFlow<Boolean> = _isLoading
    init {
        getAllWorkoutsByUser()
    }

    fun getAllWorkoutsByUser(){
        viewModelScope.launch {
            _isLoading.value = true
            _getAllWorkouts.value = workoutRepositories.getEveryWorkoutByUser()
            _isLoading.value = false
        }
    }
}