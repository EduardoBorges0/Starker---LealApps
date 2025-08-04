package com.app.starker.presentation.common.utils

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale

class TransformStringInTimestamp {
    operator fun invoke(date: String): Timestamp?{
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val timestamp = try {
            val date = format.parse(date)
            if (date != null) Timestamp(date) else null
        } catch (e: Exception) {
            null
        }
        return timestamp
    }
}