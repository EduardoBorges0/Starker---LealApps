package com.app.starker.presentation.workouts.insertWorkout

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.app.starker.R
import com.app.starker.presentation.common.view.AlertDialogComposable

@Composable
fun AlertDialogInsertWorkout(
    isEmptyField: Boolean,
    onEmptyField: (Boolean) -> Unit
) {
    if (isEmptyField) {
        AlertDialogComposable(
            onConfirm = { onEmptyField(false) },
            onDismiss = { onEmptyField(false) },
            title = stringResource(R.string.error_title),
            message = stringResource(R.string.error_empty_fields_msg)
        )
    }
}