package com.app.starker.presentation.common.utils

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale

class TransformTimestampInString {
    operator fun invoke(timestamp: Timestamp?): String {
        val date = timestamp?.toDate() // ou Date(timestamp.seconds * 1000)
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return format.format(date)
    }
}
