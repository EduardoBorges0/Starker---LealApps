package com.app.starker.data.model

import com.google.firebase.Timestamp

data class WorkoutModel (
    val name: String = "",
    val description: String = "",
    val date: Timestamp = Timestamp.now()
)