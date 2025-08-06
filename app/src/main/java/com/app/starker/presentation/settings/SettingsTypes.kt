package com.app.starker.presentation.settings

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.app.starker.R

enum class SettingsTypes(
    val title: Int,
    val description: Int,
    val imageVector: ImageVector,

) {
    LOGOUT(R.string.logout, R.string.logout_description, Icons.AutoMirrored.Filled.ExitToApp),
}