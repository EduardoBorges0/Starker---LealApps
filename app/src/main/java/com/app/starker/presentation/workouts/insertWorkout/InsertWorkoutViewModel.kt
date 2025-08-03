package com.app.starker.presentation.workouts.insertWorkout

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.starker.data.model.WorkoutModel
import com.app.starker.domain.repositories.WorkoutRepositories
import com.app.starker.domain.useCase.InsertWorkoutUseCase
import com.google.firebase.Timestamp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.sql.Time
import javax.inject.Inject

@HiltViewModel
class InsertWorkoutViewModel @Inject constructor(
    private val workoutRepositories: WorkoutRepositories,
    private val insertWorkoutUseCase: InsertWorkoutUseCase
) :
    ViewModel() {
    private val _isImageError = MutableStateFlow<Boolean>(false)
    val isImageError: StateFlow<Boolean> = _isImageError

    private val _isNavigate = MutableStateFlow<Boolean>(false)
    val isNavigate: StateFlow<Boolean> = _isNavigate

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun insertWorkout(
        name: String,
        description: String,
        date: String
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                insertWorkoutUseCase.invoke(name, description, date)
                _isNavigate.value = true
            } catch (e: Exception) {

            }
            _isLoading.value = false
        }
    }


}