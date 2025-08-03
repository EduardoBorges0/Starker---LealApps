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

    fun getWorkoutById(workoutId: String) {
        viewModelScope.launch {
            try {
                _workoutById.value = workoutRepositories.getWorkoutById(workoutId)
            } catch (e: Exception) {

            }
        }
    }

//    fun editWorkoutById(
//        workoutId: String,
//        workoutName: String,
//        descriptionName: String,
//        date: String
//    ) {
//        viewModelScope.launch {
//            val workout = WorkoutModel(
//                name = workoutName,
//                description = descriptionName,
//                date = date
//            )
//            workoutRepositories.editWorkoutByUser(workoutId)
//        }
//    }

    fun deleteWorkoutById(workoutId: String) {
        viewModelScope.launch {
            workoutRepositories.deleteWorkoutByUser(workoutId)
        }
    }
}